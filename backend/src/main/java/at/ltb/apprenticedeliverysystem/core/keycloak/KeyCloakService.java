package at.ltb.apprenticedeliverysystem.core.keycloak;

import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import at.ltb.apprenticedeliverysystem.core.keycloak.exception.KeyCloakConfigException;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserPortalDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserPortalDTO;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeyCloakService {

    private final Logger logger = LoggerFactory.getLogger(KeyCloakService.class);

    @Value("${own.jwt.auth.converter.resource-client}")
    private String resourceClient;

    @Value("${own.keycloak.realm}")
    private String realm;

    @Value("${own.keycloak.server-url}")
    private String serverUrl;

    @Value("${own.keycloak.username}")
    private String username;

    @Value("${own.keycloak.password}")
    private String password;

    @Value("${own.keycloak.client-secret}")
    private String clientSecret;

    @Value("${own.keycloak.default.password}")
    private String defaultPassword;

    public boolean loadEnabledByKeyCloakId(String keyCloakReference) {
        logger.info("LoadEnabledByKeyCloakId is called");
        UsersResource usersResource = build().realm(realm).users();
        return usersResource.get(keyCloakReference).toRepresentation().isEnabled();
    }

    public List<String> loadGroupNamesByKeyCloakId(String keyCloakReference) {
        logger.info("loadGroupNamesByKeyCloakId is called");
        UsersResource usersResource = build().realm(realm).users();
        return usersResource.get(keyCloakReference)
                .groups()
                .stream()
                .map(GroupRepresentation::getName)
                .collect(Collectors.toList());
    }

    public List<String> loadGroupIdsByKeyCloakId(String keyCloakReference) {
        logger.info("LoadGroupIdsByKeyCloakId is called");
        UsersResource usersResource = build().realm(realm).users();
        return usersResource.get(keyCloakReference)
                .groups()
                .stream()
                .map(GroupRepresentation::getId)
                .collect(Collectors.toList());
    }

    public String createKeyCloakUser(CreateUserPortalDTO user) {
        logger.info("CreateKeyCloakUser is called");
        UserRepresentation userRepresentation = mapUserRepresentation(user.email(), user.enabled());
        UsersResource usersResource = build().realm(realm).users();
        CredentialRepresentation credentialRepresentation = mapCredentialRepresentation(user.firstname());

        Response response = usersResource.create(userRepresentation);
        String userIdKeyCloak = CreatedResponseUtil.getCreatedId(response);

        UserResource createdUser = usersResource.get(userIdKeyCloak);
        createdUser.resetPassword(credentialRepresentation);

        this.updateRoles(user.roles(), createdUser);

        return userIdKeyCloak;
    }

    public void updateKeyCloakUserPortal(UpdateUserPortalDTO user, UserEntity savedUser) {
        logger.info("UpdateKeyCloakUserPortal is called");
        UsersResource usersResource = build().realm(realm).users();
        UserRepresentation foundedUser = usersResource.get(savedUser.getKeycloakReference()).toRepresentation();
        List<String> foundedGroups = this.loadGroupIdsByKeyCloakId(savedUser.getKeycloakReference());

        foundedUser.setEnabled(user.enabled());
        foundedUser.setUsername(user.email());
        foundedUser.setEmail(user.email());
        foundedUser.setEmailVerified(true);
        usersResource.get(savedUser.getKeycloakReference()).update(foundedUser);

        if (user.roles().stream().noneMatch(val -> foundedGroups.contains(val.getKeyCloakName())) || user.roles().size() != foundedGroups.size()) {
            foundedGroups.forEach(val -> usersResource.get(savedUser.getKeycloakReference()).leaveGroup(val));
            this.updateRoles(user.roles(), usersResource.get(savedUser.getKeycloakReference()));
        }
    }

    public void updateKeyCloakUser(UpdateUserDTO user, UserEntity savedUser) {
        logger.info("UpdateKeyCloakUser is called");
        UsersResource usersResource = build().realm(realm).users();
        UserRepresentation foundedUser = usersResource.get(savedUser.getKeycloakReference()).toRepresentation();

        foundedUser.setUsername(user.email());
        foundedUser.setEmail(user.email());
        foundedUser.setEmailVerified(true);
        usersResource.get(savedUser.getKeycloakReference()).update(foundedUser);
    }

    private Keycloak build() {
        Keycloak keycloak = null;
        try {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .clientId(resourceClient)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(username)
                    .password(password)
                    .clientSecret(clientSecret)
                    .build();
        } catch (Exception e) {
            logger.error("KeyCloakConfig throws exception!");
            throw new KeyCloakConfigException("not able to configure keycloak");
        }
        return keycloak;
    }

    private UserRepresentation mapUserRepresentation(String email, Boolean enabled) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(enabled);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setUsername(email);
        userRepresentation.setEmail(email);
        return userRepresentation;
    }

    private CredentialRepresentation mapCredentialRepresentation(String firstName) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(defaultPassword + firstName);
        return passwordCred;
    }

    private void updateRoles(List<RoleEnum> roles, UserResource user) {
        List<GroupRepresentation> existingGroups = build().realm(realm).groups().groups();
        roles.forEach(roleVal -> {
            GroupRepresentation searchedGroup = existingGroups
                    .stream()
                    .filter(val -> val.getName().equals(roleVal.getKeyCloakName()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            user.joinGroup(searchedGroup.getId());
        });
    }

}
