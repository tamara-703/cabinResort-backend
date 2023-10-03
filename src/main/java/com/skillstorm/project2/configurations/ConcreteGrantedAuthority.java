package com.skillstorm.project2.configurations;



import org.springframework.security.core.GrantedAuthority;


public class ConcreteGrantedAuthority implements GrantedAuthority {

   private String authority;







   public ConcreteGrantedAuthority(String authority) {

       this.authority = authority;

   }







   @Override

   public String getAuthority() {

       return authority;

   }

}
