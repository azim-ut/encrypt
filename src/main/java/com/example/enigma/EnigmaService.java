package com.example.enigma;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnigmaService {

    private final EnigmaConfig enigmaConfig;

    private static String symbols;
    static {
        IntStream digits = IntStream.rangeClosed('0', '9');
        IntStream az = IntStream.rangeClosed('a', 'z');
        IntStream azCup = IntStream.rangeClosed('A', 'Z');

        IntStream part1 = IntStream.concat(digits, az);
        IntStream out = IntStream.concat(part1, azCup);

        symbols = out.mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
        symbols += ":";
    }

    public String getSymbols(){
        return symbols;
    }

    public String encrypt(String data){
        StringBuilder res = new StringBuilder();
        String key = enigmaConfig.getKey();
        int keyIndex = 0;
        for(int i = 0; i<data.length(); i++){
            char el = data.charAt(i);
            char encodedEl = el;
            if(symbols.indexOf(el) >= 0){
                int keyElementPosition = symbols.indexOf(key.charAt(keyIndex));
                int encodedElementPosition = symbols.indexOf(el) + keyElementPosition;
                if(encodedElementPosition >= symbols.length()){
                    encodedElementPosition = encodedElementPosition - symbols.length();
                }
                encodedEl = symbols.charAt(encodedElementPosition);
            }
            res.append(encodedEl);
            keyIndex++;
            if(keyIndex > key.length()-1){
                keyIndex = 0;
            }
        }
        return res.toString();
    }

    public String decrypt(String data){
        StringBuilder res = new StringBuilder();
        String key = enigmaConfig.getKey();
        int keyIndex = 0;
        for(int i = 0; i<data.length(); i++){
            char el = data.charAt(i);
            char encodedEl = el;
            if(symbols.indexOf(el) >= 0){
                int keyElementPosition = symbols.indexOf(key.charAt(keyIndex));
                int encodedElementPosition = symbols.indexOf(el) - keyElementPosition;
                if(encodedElementPosition < 0){
                    encodedElementPosition = symbols.length() + encodedElementPosition;
                }
                encodedEl = symbols.charAt(encodedElementPosition);
            }
            res.append(encodedEl);
            keyIndex++;
            if(keyIndex > key.length()-1){
                keyIndex = 0;
            }
        }
        return res.toString();
    }
}
