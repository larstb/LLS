package at.ltb.apprenticedeliverysystem.core.user.crud;

import at.ltb.apprenticedeliverysystem.configuration.permission.AdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserPaginationRepository;
import at.ltb.apprenticedeliverysystem.core.user.crud.mapper.UserRequestMapper;
import at.ltb.apprenticedeliverysystem.core.user.crud.mapper.UserResponseMapper;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserCreateDTO;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserDTO;
import at.ltb.apprenticedeliverysystem.core.user.crud.models.UserOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.user.validator.UserEmailValidator;
import at.ltb.apprenticedeliverysystem.core.user.validator.UserPhoneNumberValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserCrudApi {

    private final UserPaginationRepository userPaginationRepository;

    private final UserCrudRepository userCrudRepository;

    private final UserResponseMapper userResponseMapper;

    private final UserRequestMapper userRequestMapper;

    private final UserPhoneNumberValidator userPhoneNumberValidator;

    private final UserEmailValidator userEmailValidator;

    public UserCrudApi(UserPaginationRepository userPaginationRepository,
                       UserCrudRepository userCrudRepository,
                       UserResponseMapper userResponseMapper,
                       UserRequestMapper userRequestMapper, UserPhoneNumberValidator userPhoneNumberValidator,
                       UserEmailValidator userEmailValidator) {
        this.userPaginationRepository = userPaginationRepository;
        this.userCrudRepository = userCrudRepository;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
        this.userPhoneNumberValidator = userPhoneNumberValidator;
        this.userEmailValidator = userEmailValidator;
    }

    @AdminPermission
    @GetMapping(value = "/")
    public ResponseWrapper<UserOverviewDTO> getUserOverView(@RequestParam(value = "page",
                                                                required = false) Integer page,
                                                            @RequestParam(value = "size",
                                                                required = false) Integer size) {
        return new ResponseWrapper<>(
                userResponseMapper.map(userPaginationRepository.findAll(
                        PaginationUtil.getPagination(page, size)).getContent()), userCrudRepository.count());
    }

    @AdminPermission
    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userResponseMapper.mapDto(userCrudRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @AdminPermission
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        userPhoneNumberValidator.validate(userCreateDTO.phoneNumber());
        userEmailValidator.validateWithoutUniqueCheck(userCreateDTO.email());
        UserEntity userToSave = userRequestMapper.mapCreate(userCreateDTO);
        return userResponseMapper.mapDto(userCrudRepository.save(userToSave));
    }

    @AdminPermission
    @PutMapping(value = "/")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        UserEntity oldUser = userCrudRepository.findById(userDTO.id()).orElseThrow(EntityNotFoundException::new);
        userPhoneNumberValidator.validate(userDTO.phoneNumber());
        userEmailValidator.validateWithoutUniqueCheck(userDTO.email());
        userRequestMapper.mapUpdate(oldUser, userDTO);
        return userResponseMapper.mapDto(userCrudRepository.save(oldUser));
    }
}
