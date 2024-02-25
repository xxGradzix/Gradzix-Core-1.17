package me.xxgradzix.gradzixcore.clansCore.data.database.utills;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

public class ClanSetPersister extends StringType {

    private static final ClanSetPersister INSTANCE = new ClanSetPersister();

    private ClanSetPersister() {
        super(SqlType.STRING, new Class<?>[] { Set.class });
    }

    public static ClanSetPersister getSingleton() {
        return INSTANCE;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        Set<ClanEntity> myFieldClass = (Set<ClanEntity>) javaObject;
        return myFieldClass != null ? getJsonFromSet(myFieldClass) : null;
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        try {
            return sqlArg != null ? getSetFromJson((String) sqlArg) : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getJsonFromSet(Set<ClanEntity> members) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);


            dataOutput.writeObject(members);


            dataOutput.close();

            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<ClanEntity> getSetFromJson(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            Set<ClanEntity> members = (Set<ClanEntity>) dataInput.readObject();

            dataInput.close();
            return members;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}