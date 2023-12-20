package at.ltb.apprenticedeliverysystem.core.user.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO implements BaseResponse {

    private String id;

    private Integer version;

    private String firstname;

    private String lastname;

    private String status;

    private String phoneNumber;

    private String location;

    private String iban;

    private String paypalLink;

    private String email;
}
