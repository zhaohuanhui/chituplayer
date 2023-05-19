package com.example.player.login;

public class UserBean{
   String name;
   String id;
   String account;
   String passWord;

   public UserBean(String name, String id, String account, String passWord) {
       this.name = name;
       this.id = id;
       this.account = account;
       this.passWord = passWord;
   }

   public String getId() {
       return id;
   }

   public void setId(String id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getAccount() {
       return account;
   }

   public void setAccount(String account) {
       this.account = account;
   }

   public String getPassWord() {
       return passWord;
   }

   public void setPassWord(String passWord) {
       this.passWord = passWord;
   }
}
