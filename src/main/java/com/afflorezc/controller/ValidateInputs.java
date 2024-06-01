package com.afflorezc.controller;

import java.util.Enumeration;
import jakarta.servlet.http.HttpServletRequest;

public class ValidateInputs {

    public static boolean validateNoVoidFields(HttpServletRequest request){

        Enumeration<String> parametersNames = request.getParameterNames();

        if(!parametersNames.hasMoreElements()){
            return false;
        }
        while(parametersNames.hasMoreElements()){

            String parameterName = parametersNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            if(parameterValue == null || parameterValue.equals("")){
                return false;
            }
        }
        return true;
    }

}
