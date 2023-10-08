package co.emp.prisig.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicio {

    private Cloudinary cloudinary;
    private Map<String, String> config;

    public CloudinaryServicio(){

        config = new HashMap<>();
        config.put("cloud_name", "dcdp3aquu");
        config.put("api_key", "916272861367715");
        config.put("api_secret", "ZP9GirrVmOD_GvwWWCFC-qtwXTM");

        cloudinary = new Cloudinary(config);
    }

    public Map subirImagen(File file, String carpeta)throws Exception{
        Map respuesta = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", String.format("prisig/%s", carpeta)));
        return respuesta;
    }

    public Map eliminarImagen(String idImagen)throws Exception{
        Map respuesta = cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
        return respuesta;
    }

}
