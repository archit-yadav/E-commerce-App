package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private List<User> userList= new ArrayList<>();

    public List<UserResponse> getAllUser(){
//        return userList;
          return userRepository.findAll().stream()
                  .map(this::mapToResponse)
                  .collect(Collectors.toList());
     }

    public void createUser( UserRequest userRequest){
//        user.setId(nextId++);
//        userList.add(user);

        User user = new User();
        updateUserFromRequest(user,userRequest);

          userRepository.save(user);
    }

    public UserResponse getUserById(int id) {

//        return userList.stream().filter(user -> user.getId().equals((long)id)).findFirst().
//                orElseGet(User::new);

        return userRepository.findById(Long.valueOf(id)).map(this::mapToResponse).orElse(null);


//        for (User user: userList){
//            if(user.getId() == id )
//                return user;
//        }
//        return null;

    }

    public void updateUser(int id, UserRequest updatedUserRequest) {
//        userList.stream().filter(n -> n.getId().equals((long)id)).findFirst().
//                ifPresent(n -> {
//                    int i = userList.indexOf(n);
//                    user.setId((long) id);
//                    userList.set(i, user);
//
//                });



         userRepository.findById((long) id).map(
                 n -> {

                     updateUserFromRequest(n,updatedUserRequest);
                     userRepository.save(n);
                     return null;
                 });

    }

    public void deleteUser(int id) {

        userRepository.deleteById((long) id);

    }





    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if(userRequest.getAddress()!=null){
            Address address = new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setZipcode(userRequest.getAddress().getZipCode());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            user.setAddress(address);
        }
    }


    private UserResponse mapToResponse(User user){

        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress()!=null){

            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipCode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;

    }
}


