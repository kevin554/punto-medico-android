package com.bo.punto.medico.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.bo.punto.medico.models.User;
import com.google.gson.Gson;

import org.json.JSONObject;


public class Preferences {

    private static final String KEY_SHARED_PREFERENCE = "myPref";
    private static final String KEY_USER= "USER";

    public static User getAlumno(Context context) {
        SharedPreferences pref = context.getSharedPreferences(KEY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        String alumnoJson = pref.getString(KEY_USER, "");

        if (alumnoJson.length() <= 0) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(alumnoJson, User.class);
    }

    public static void setUser(Context context, User obj) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        editor.putString(KEY_USER, gson.toJson(obj));
        editor.apply();
    }

    public static void setUser(Context context, JSONObject json) {
//        SharedPreferences pref = context.getSharedPreferences(KEY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        Gson gson = new Gson();
//
//        Alumno alumno = new Alumno();
//
//        try {
//            if (!json.isNull("LALUMNO_ID")) alumno.setId(json.getInt("LALUMNO_ID"));
//            alumno.setRegistro(json.isNull("SREGISTRO") ? "" : json.get("SREGISTRO").toString().trim());
//            alumno.setApellidoPaterno(json.isNull("SAPELLIDOP") ? "" : json.get("SAPELLIDOP").toString().trim());
//            alumno.setApellidoMaterno(json.isNull("SAPELLIDOM") ? "" : json.get("SAPELLIDOM").toString().trim());
//            alumno.setNombre(json.isNull("SNOMBRES") ? "" : json.get("SNOMBRES").toString().trim());
//            alumno.setFechaNacimiento(json.isNull("DTFECHNAC") ? "" : json.get("DTFECHNAC").toString().trim());
//            alumno.setSexo(json.isNull("SSEXO_DSC") ? "" : json.get("SSEXO_DSC").toString().trim());
//            alumno.setCelular(json.isNull("SCELULAR") ? "" : json.get("SCELULAR").toString().trim());
//            alumno.setColegio(json.isNull("SCOLEGIO") ? "" : json.get("SCOLEGIO").toString().trim());
//            alumno.setTipoColegio(json.isNull("STIPOCOLEGIO") ? "" : json.get("STIPOCOLEGIO").toString().trim());
//            alumno.setTelefono(json.isNull("STELEFONO") ? "" : json.get("STELEFONO").toString().trim());
//            alumno.setEmail(json.isNull("SEMAIL") ? "" : json.get("SEMAIL").toString().trim());
//            alumno.setEstadoCivil(json.isNull("SESTADOCIVIL_DSC") ? "" : json.get("SESTADOCIVIL_DSC").toString().trim());
//            alumno.setTipoSangre(json.isNull("STIPOSANGRE_DSC") ? "" : json.get("STIPOSANGRE_DSC").toString().trim());
//            if (!json.isNull("BOOLACTIVOPASIVO"))
//                alumno.setActivoPasivo(json.getInt("BOOLACTIVOPASIVO") == 1);
//            if (!json.isNull("LHORASERVICIO"))
//                alumno.setHorasServicio(json.getInt("LHORASERVICIO"));
//        } catch (Exception ignored) {
//        }
//
//        editor.putString(KEY_ALUMNO_INFO, gson.toJson(alumno));
//        editor.commit();
    }

}
