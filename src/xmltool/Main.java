package xmltool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    //실행형 자바 파일 void main 메서드
    public static void main(String[] args) throws Exception {

        //특정 폴더의 하위 listfiles를 file배열로 선언
        File[] filelist = new File("C:\\borinet\\input").listFiles();

        //파일 개수만큼 반복문
        for(int i=0;i<filelist.length;i++) {
            //파일을 변경하며 복사하는 메서드로 보냄
            FileChange(filelist[i]);
        }
    }

    static FileReader fr;
    static BufferedReader br;
    static FileWriter fw;
    static BufferedWriter bw;

    public static void FileChange(File f) throws Exception {
        //중간경로가 input_new라는 폴더에 원본파일명과 동일한 이름으로 파일 객체 생성
        File nf = new File("C:\\borinet\\input_new\\"+f.getName());
        //신규경로인 input_new 폴더에 신규파일 라이터와 버퍼라이터를 선언
        fw = new FileWriter(nf);
        bw = new BufferedWriter(fw);
        //기존 input 폴더의 원본파일 리더와 버퍼리더 선언
        fr = new FileReader(f);
        br = new BufferedReader(fr);

        //파일을 한줄씩 읽어서 담을 스트링 탬프 변수
        String s;
        //해당 줄을 옮겨적을지 여부를 판단하는 boolean 변수
        boolean wgb = true;

        //원본파일의 줄읽기가 null이 나올때까지 한줄씩 읽음 반복문
        while ((s = br.readLine()) != null) {
            //줄안에 <!-- 주석시작 문구가 있다면
            if(s.indexOf("<!--") > -1) {
                //옮겨적지 않도록
                wgb = false;
                //줄안에 --> 주석종료 문구가 있다면
            }else if(s.indexOf("-->") > -1) {
                //한줄 더 읽어서 다음줄로 바꾸고
                s = br.readLine();
                //옮겨적도록
                wgb = true;
            }
            //옮겨적기 일 경우
            if(wgb) {
                //줄 쓰기
                bw.write(s);
                //한줄내림(엔터)
                bw.newLine();
            }
        }
        //파일 밀어넣기 (버퍼에 담긴 데이터 파일로 기록)
        bw.flush();

        //사용 변수들 초기화
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
}