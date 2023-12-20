/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Pascal
 */
public class FxUtil {

    public static String genId() {
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                boolean uuidStringMatcher = id.matches(".*[a-zA-Z]+.*");

                if (uuidStringMatcher == false) {
                    Random random = new Random();
                    char cha = (char) (random.nextInt(26) + 'a');
                    int numToReplace = random.nextInt(9);

                    id = id.replaceAll(String.valueOf(numToReplace), String.valueOf(cha));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
