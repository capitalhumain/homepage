package org.greenrivers.java.controller;

import io.swagger.annotations.*;
import org.greenrivers.java.transferobject.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value="/user", protocols="http", description="Operations pertaining to users in Online Store")
public class UserController {
    private List<User> dummyUsers = new ArrayList<>();

    @RequestMapping(value="list", method= RequestMethod.GET, produces = "application/json")
    @ApiOperation(value="View a list of users", response=User.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Successful retrieved list")
    })
    public List<User> list() {
        return dummyUsers;
    }

    @RequestMapping(value="show/{id}", method= RequestMethod.GET, produces = "application/json")
    @ApiOperation(value="get user by user id", response=User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Successful retrieved list"),
            @ApiResponse(code = 404, message="user not found")
    })
    public User show(@PathVariable("id")
                         @ApiParam(value="user id") Integer id, HttpServletResponse response) {
        if(id > 0) {
            return new User(1, "Test");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
