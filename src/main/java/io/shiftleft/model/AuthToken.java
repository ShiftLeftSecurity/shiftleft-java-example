package io.shiftleft.model;

import java.io.Serializable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class AuthToken implements Serializable {
  private static final long serialVersionUID = 1L;

  // yes there are only 2 roles so
  // having them in this class should be fine
  public static int ADMIN = 0;
  public static int USER = 1;

  private int role;

  public AuthToken(int role) {
    this.role = role;
  }

  public boolean isAdmin() {
    return this.role == ADMIN;
  }

  public int getRole() {
    if(this.role == ADMIN) {
      return ADMIN;
    } else {
      return USER;
    }
  }

  public void setRole(int role) {
    this.role = role;
  }

  public String toBase64() {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(this);
      return new String(Base64.getEncoder().encode(bos.toByteArray()));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
