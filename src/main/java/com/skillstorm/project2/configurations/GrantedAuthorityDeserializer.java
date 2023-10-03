package com.skillstorm.project2.configurations;

import java.io.IOException;



import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class GrantedAuthorityDeserializer extends StdDeserializer<GrantedAuthority> {

    public GrantedAuthorityDeserializer() {

        this(null);

    }

    public GrantedAuthorityDeserializer(Class<?> vc) {

        super(vc);

    }

    @Override

    public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

 

        // Extract necessary information from the JSON node and create a concrete GrantedAuthority instance

        //String authorityName = node.get("ROLE_USER").asText(); // Assuming "authority" is a property in your JSON

        	String authorityName = "ROLE_USER";

 

 

        // Create a concrete instance of your custom GrantedAuthority implementation

        ConcreteGrantedAuthority concreteAuthority = new ConcreteGrantedAuthority(authorityName);
 

        return concreteAuthority;

    }

}
