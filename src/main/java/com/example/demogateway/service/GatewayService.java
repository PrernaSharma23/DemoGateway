package com.example.demogateway.service;

import com.example.demogateway.model.GatewayUser;
import com.example.demogateway.model.GatewayUserList;
import com.example.demogateway.repository.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GatewayService {

    @Autowired
    private ServerClient serverClient;

    public GatewayUser getTransformedUser(Integer userId) {
        Object obj = serverClient.serverGet("/user/" + userId);
        if (!(obj instanceof Map)) {
            throw new IllegalStateException("Unexpected upstream response");
        }
        Map<String, Object> data = (Map<String, Object>) obj;
        return new GatewayUser(
                (Integer) data.get("id"),
                (String) data.get("name"),
                (String) data.get("email")
        );
    }

    public GatewayUserList getTransformedUsers() {
        Object obj = serverClient.serverGet("/user/");
        if (!(obj instanceof List)) {
            throw new IllegalStateException("Unexpected upstream response");
        }
        List<?> raw = (List<?>) obj;
        List<GatewayUser> users = new ArrayList<>();
        for (Object o : raw) {
            if (!(o instanceof Map)) continue;
            Map<String, Object> u = (Map<String, Object>) o;
            Integer id = (Integer) u.get("id");
            String name = (String) u.get("name");
            String email = (String) u.get("email");
            users.add(new GatewayUser(id, name, email));
        }
        return new GatewayUserList(users);
    }
}
