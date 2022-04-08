package HashAlgorithm;

import java.security.MessageDigest; //SHA, MD5, SHA-1, SHA-512 .. 기타 해시 알고리즘을 사용할 수 있는 라이브러리 호출
import java.security.NoSuchAlgorithmException; // 특정 암호화 알고리즘이 요청되었으나, 환경에서 사용할 수 없을 때(예외사항)호출

public class SHA256 {

    public String encrypt(String text) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // sha256을 사용할 수 있도록 인스턴스 호출
        md.update(text.getBytes()); // update 즉 main 에서 비밀번호가 입력되면 sha256 알고리즘을 통해 특수문자로 바뀜
        return byteToHex(md.digest()); // 해쉬로 처리된 비밀번호를 리턴함
    }

    private String byteToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder(); // 비밀번호와 같이 긴 문자열은 StringBuilder 를 통해 메모리 할당을 줄여주어야 함
        for(byte b : bytes){
            builder.append(String.format("%02x", b)); // b&0xff 를 2자리 Hex String 으로 출력함 ,b&0xff 비트 연산은 앞에 비트가 의도치 않게 1로 채워졌을 때 0으로 바꿔주기 위함

        }
        return builder.toString(); // StringBuilder 를 문자열로 바꿈 --> 나누어진 Hex 값을 다시 문자열로 보여줌
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256(); // sha 256 객체 생성
        //해당 user 비밀번호
        String passwd = "mjc2018123456";
        //SHA256으로 암호화된 비밀번호
        String cryptogram = sha256.encrypt(passwd);

        System.out.println(cryptogram);
        //비밀번호 일치 여부
       System.out.println(cryptogram.equals(sha256.encrypt(passwd)));
    }
}
