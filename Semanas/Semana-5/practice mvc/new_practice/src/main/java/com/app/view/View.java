package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Contrato que toda vista debe cumplir.
 * El Controller solo conoce esta interfaz.
 */
public interface View {
    void       showMessage(String msg);
    void       showError(String msg);
    void       showUsuarios(List<Usuario> usuarios);
    void       showUsuario(Usuario usuario);
    String     askInput(String prompt);
    boolean    confirm(String question);
    void       showMenu(String[] options, String title);
    int        getMenuChoice();
}