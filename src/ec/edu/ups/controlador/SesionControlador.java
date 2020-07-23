package ec.edu.ups.controlador;


import ec.edu.ups.entidad.Usuario;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class SesionControlador implements Serializable {

    private Usuario empleado;

    public void verificarSesion(){
        try {
            FacesContext context= FacesContext.getCurrentInstance();
             empleado= (Usuario) context.getExternalContext().getSessionMap().get("empleado");
            if(empleado!=null){
                if (empleado.getRol().getDescripcion().equals("administrador")){
                    context.getExternalContext().redirect("/Practica-4/PrivadaAdm/IndexAdm.xhtml");
                }else {
                    context.getExternalContext().redirect("/Practica-4/PrivadaEmp/GestionEmpleados.xhtml");
                }
            }
        }catch (Exception exception){

        }
    }

    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Practica-4/publica/Login.xhtml");
        empleado=new Usuario();
    }
    

    public void validarRolAdministrador(){
    	System.out.println("VALIDANDO ADMINISTRADOR..");
        try {
            FacesContext context= FacesContext.getCurrentInstance();
            empleado= (Usuario) context.getExternalContext().getSessionMap().get("empleado");
            if(empleado==null){
                context.getExternalContext().redirect("/Practica-4/publica/Login.xhtml");
            }else {
                if (empleado.getRol().getDescripcion().equals("administrador")){
                //    context.getExternalContext().redirect("/Practica-4/PrivadaAdm/IndexAdm.xhtml");
                }
            }

        }catch (Exception exception){

        }
    }

    public void validarRolVendedor(){
    	System.out.println("VALIDANDO VENDERDOR..");
        try {
            FacesContext context= FacesContext.getCurrentInstance();
            empleado= (Usuario) context.getExternalContext().getSessionMap().get("Empleado");
            if(empleado==null){
                context.getExternalContext().redirect("/Practica-4/publica/Login.xhtml");
            }else {
                if (empleado.getRol().getDescripcion().equals("empleado")){
                 //   context.getExternalContext().redirect("/Practica-4/PrivadaEmp/GestionEmpleados.xhtml");
                }
            }

        }catch (Exception exception){

        }
    }
    


    public Usuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Usuario empleado) {
        this.empleado = empleado;
    }
}