package me.xxgradzix.gradzixcore.playerSettings.data.database.persisters;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class HashMapItemStackItemStackType extends StringType {

    private static final HashMapItemStackItemStackType instance = new HashMapItemStackItemStackType();

    private HashMapItemStackItemStackType() {
        super(SqlType.STRING, new Class<?>[]{HashMap.class});
    }

    public static HashMapItemStackItemStackType getSingleton() {
        return instance;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        if (javaObject != null && javaObject instanceof HashMap) {
            HashMap<?, ?> hashMap = (HashMap<?, ?>) javaObject;
            return encodeItemStackMap((HashMap<ItemStack, ItemStack>) hashMap);
        }
        return null;
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        if (sqlArg != null && sqlArg instanceof String) {
            String jsonString = (String) sqlArg;
            return decodeItemStackMap(jsonString);
        }
        return null;
    }
    private static String encodeItemStackMap(HashMap<ItemStack, ItemStack> itemStackMap) {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(itemStackMap);

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    private static HashMap<ItemStack, ItemStack> decodeItemStackMap(String data) {

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            try {
                return (HashMap<ItemStack, ItemStack>) dataInput.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}