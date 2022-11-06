package com.zj.Main;


import com.zj.controller.PersonController;
import com.zj.view.loginPlatform;

public class PersonMain extends loginPlatform {
    @Override
    public void showPersonAdmin() {
        new PersonController().setVisible(true);
    }

    public static void main(String[] args) {
        new PersonMain().setVisible(true);
    }
}
