package controller;

import view.MainFrame;

public class Controller {

    MainFrame mainFrame;

    public Controller() {
        mainFrame = new MainFrame(1280, 720, this);
    }

    public String[] getSpecializations() {
        String[] specializations = {"Dentist", "GP", "Surgeon"};
        return specializations;
    }

    public String[][] getDoctorsBySpecialization(String specialization) {
        String[][] doctors = {
                {"PoopDude69", "Dentist", "$420"},
                {">WeedGuy<", "Apothecary", "$6969"}
        };
        return doctors;
    }


}
