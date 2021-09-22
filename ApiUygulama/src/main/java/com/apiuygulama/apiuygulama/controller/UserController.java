package com.apiuygulama.apiuygulama.controller;

import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.model.User;
import com.apiuygulama.apiuygulama.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user")
@Api(value = "User API documentation")
public class UserController {
    @Autowired UserRepository userRepository;

    @GetMapping("/getAllUser")
    @ApiOperation(value = "Kullanıcı Sayısını Getirir.")
    public ResponseEntity<Integer> getUserCount(){
        return new ResponseEntity<>(userRepository.userCount(), OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Kullanıcılar Getirme İşlemi")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<>(userRepository.findAll(), OK);
        } else {
            return new ResponseEntity<>(userRepository.findAllByUsername(name), OK);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Foods Silme İşlemi")
    public ResponseEntity<?> deleteUser(@RequestParam(required = true) Long id){
        userRepository.deleteById(id);
        return  new ResponseEntity<>(OK);
    }
}
