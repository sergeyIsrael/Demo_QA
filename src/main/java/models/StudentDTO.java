package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Setter
@Getter
@Builder
@ToString

public class StudentDTO {

    String firstName;
    String lastName;
    String email;
    Gender gender;
    String phone;
    String birthday;
    String subject;
    List<Hobby> hobbies;
    String address;
    String state;
    String city;

}
