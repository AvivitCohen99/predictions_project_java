package world.property.generator.random.impl.string;

import world.property.generator.random.api.AbstractRandomValueGenerator;

import java.security.SecureRandom;

public class RandomStringValueGenerator extends AbstractRandomValueGenerator<String> {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!?_-.() ";
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 50;

    @Override
    public String generateValue() {
        int length = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = new SecureRandom().nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
