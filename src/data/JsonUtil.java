// Adicione a dependência do Gson ao seu projeto Java para manipular JSON.
// Exemplo para Maven:
// <dependency>
//   <groupId>com.google.code.gson</groupId>
//   <artifactId>gson</artifactId>
//   <version>2.10.1</version>
// </dependency>

package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Usuario;
import model.Projeto;
import java.io.*;
import java.util.*;

public class JsonUtil {
    private static final Gson gson = new Gson();
    private JsonUtil() {}

    public static <T> List<T> readListFromJson(String filePath, TypeToken<List<T>> typeToken) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, typeToken.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static <T> void writeListToJson(String filePath, List<T> list) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            // Tratar erro
        }
    }
}
