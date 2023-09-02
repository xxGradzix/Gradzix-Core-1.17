package me.xxgradzix.gradzixcore.ustawienia.data.database.persisters;

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

public class HashMapItemStackIntegerType extends StringType {

    private static final HashMapItemStackIntegerType instance = new HashMapItemStackIntegerType();

    private HashMapItemStackIntegerType() {
        super(SqlType.STRING, new Class<?>[]{HashMap.class});
    }

    public static HashMapItemStackIntegerType getSingleton() {
        return instance;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        if (javaObject != null && javaObject instanceof HashMap) {
            HashMap<?, ?> hashMap = (HashMap<?, ?>) javaObject;
            return encodeItemStackMap((HashMap<ItemStack, Integer>) hashMap);
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
    private static String encodeItemStackMap(HashMap<ItemStack, Integer> itemStackMap) {
//        HashMap<String, String> serializedMap = new HashMap<>();
//
//        for (Map.Entry<ItemStack, ItemStack> entry : itemStackMap.entrySet()) {
//            ItemStack key = entry.getKey();
//            ItemStack value = entry.getValue();
//
//            String serializedKey = encodeItem(key);
//            String serializedValue = encodeItem(value);
//
//            serializedMap.put(serializedKey, serializedValue);
//        }
//
//        Gson gson = new Gson();
//        String json = gson.toJson(serializedMap);
//
//        return json;


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
    private static HashMap<ItemStack, Integer> decodeItemStackMap(String data) {
//        Gson gson = new Gson();
//        HashMap<String, String> serializedMap = gson.fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
//
//        HashMap<ItemStack, ItemStack> itemStackMap = new HashMap<>();
//
//        for (Map.Entry<String, String> entry : serializedMap.entrySet()) {
//            String serializedKey = entry.getKey();
//            String serializedValue = entry.getValue();
//            ItemStack key = decodeItem(serializedKey);
//            ItemStack value = decodeItem(serializedValue);
//            itemStackMap.put(key, value);
//        }
//
//        return itemStackMap;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            try {
                return (HashMap<ItemStack, Integer>) dataInput.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


//    private static String encodeItem(ItemStack itemStack) {
//        YamlConfiguration config = new YamlConfiguration();
//        config.set("i", itemStack);
//        byte[] bytes = config.saveToString().getBytes(StandardCharsets.UTF_8);
//        byte[] encodedBytes = Base64.encodeBase64(bytes);
//        return new String(encodedBytes, StandardCharsets.UTF_8);
//    }
//
//    private static ItemStack decodeItem(String string) {
//        byte[] decodedBytes = Base64.decodeBase64(string.getBytes(StandardCharsets.UTF_8));
//        YamlConfiguration config = new YamlConfiguration();
//        try {
//            config.loadFromString(new String(decodedBytes, StandardCharsets.UTF_8));
//        } catch (IllegalArgumentException | InvalidConfigurationException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return config.getItemStack("i", null);
//    }
}