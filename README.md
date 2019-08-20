# r2_semi
kh r2 semi 프로젝트


# 미비된 점
멤버 탈퇴시 댓글,글 자동으로 update

# oracle - function

--초성

create or replace function fn_choSeong( str in varchar2) return varchar2 
is 
   returnStr varchar2(100);  
   cnt number := 0;  
   tmpStr varchar2(10); 
begin  
      if str is null then 
      return ''; 
      end if;  

      cnt := length(str);  

      for i in 1 .. cnt 
      loop 
        tmpStr := substr(str,i,1);        
      returnStr := returnStr || 
      case when tmpStr < 'ㄱ' then substr(tmpStr, 1, 1) 
            when ascii('ㄱ') <= ascii(tmpStr) and ascii(tmpStr) <= ascii('ㅎ') then chr(ascii(tmpStr)) 
            when tmpStr < '나' then 'ㄱ' 
            when tmpStr < '다' then 'ㄴ' 
            when tmpStr < '라' then 'ㄷ' 
            when tmpStr < '마' then 'ㄹ' 
            when tmpStr < '바' then 'ㅁ' 
            when tmpStr < '사' then 'ㅂ' 
            when tmpStr < '아' then 'ㅅ' 
            when tmpStr < '자' then 'ㅇ' 
            when tmpStr < '차' then 'ㅈ' 
            when tmpStr < '카' then 'ㅊ' 
            when tmpStr < '타' then 'ㅋ' 
            when tmpStr < '파' then 'ㅌ' 
            when tmpStr < '하' then 'ㅍ' 
            else 'ㅎ' 
      end;  
      end loop; 
      return returnStr; 
end; 



--초성 중성 종성 나누기



create or replace FUNCTION FN_DIV_KOR (
    i_p1    IN    VARCHAR2
)
    RETURN VARCHAR2
AS
    l_rt    VARCHAR2 (4000);


    FUNCTION f1 (
        i_p1    IN    VARCHAR2
    )
        RETURN VARCHAR2
    AS
        l_p1    PLS_INTEGER     := ASCII (UNISTR (i_p1));
        l_i0    PLS_INTEGER     := l_p1 - TO_NUMBER ('AC00', 'XXXX');
        l_i1    PLS_INTEGER     := TO_NUMBER ('1100', 'XXXX') + FLOOR (l_i0 / (21 * 28));
        l_i2    PLS_INTEGER     := TO_NUMBER ('1161', 'XXXX') + FLOOR (MOD (l_i0, (21 * 28)) / 28);
        l_i3    PLS_INTEGER     := TO_NUMBER ('11A7', 'XXXX') + FLOOR (MOD (l_i0, 28));
        l_rt    VARCHAR2(4000);
    BEGIN
        CASE
            WHEN l_p1 BETWEEN TO_NUMBER ('AC00', 'XXXX') AND TO_NUMBER ('D7A3', 'XXXX')
            THEN
                l_rt := NCHR (l_i1)
                     || CASE WHEN l_i2 <> 4519 THEN NCHR (l_i2) END
                     || CASE WHEN l_i3 <> 4519 THEN NCHR (l_i3) END
                     ;
            ELSE
                l_rt := i_p1;
        END CASE;

        RETURN l_rt;
    END f1;


    FUNCTION f2 (
        i_p1    IN    VARCHAR2
    )
        RETURN VARCHAR2
    AS
        l_p1    VARCHAR2(10);
        l_rt    VARCHAR2(30);
    BEGIN

        FOR i IN 1.. LENGTH( i_p1 )
        LOOP

            l_p1 := SUBSTR(i_p1, i, 1);

            IF l_p1 = 'ᆨ' OR l_p1 = 'ᄀ' THEN l_p1 := 'ㄱ';
            ELSIF l_p1 = 'ᆫ' OR l_p1 = 'ᄂ' THEN l_p1 := 'ㄴ';
            ELSIF l_p1 = 'ᆮ' OR l_p1 = 'ᄃ' THEN l_p1 := 'ㄷ';
            ELSIF l_p1 = 'ᆯ' OR l_p1 = 'ᄅ' THEN l_p1 := 'ㄹ';
            ELSIF l_p1 = 'ᆷ' OR l_p1 = 'ᄆ' THEN l_p1 := 'ㅁ';
            ELSIF l_p1 = 'ᆸ' OR l_p1 = 'ᄇ' THEN l_p1 := 'ㅂ';
            ELSIF l_p1 = 'ᆺ' OR l_p1 = 'ᄉ' THEN l_p1 := 'ㅅ';
            ELSIF l_p1 = 'ᆼ' OR l_p1 = 'ᄋ' THEN l_p1 := 'ㅇ';
            ELSIF l_p1 = 'ᆽ' OR l_p1 = 'ᄌ' THEN l_p1 := 'ㅈ';
            ELSIF l_p1 = 'ᆾ' OR l_p1 = 'ᄎ' THEN l_p1 := 'ㅊ';
            ELSIF l_p1 = 'ᆿ' OR l_p1 = 'ᄏ' THEN l_p1 := 'ㅋ';
            ELSIF l_p1 = 'ᇀ' OR l_p1 = 'ᄐ' THEN l_p1 := 'ㅌ';
            ELSIF l_p1 = 'ᇁ' OR l_p1 = 'ᄑ' THEN l_p1 := 'ㅍ';
            ELSIF l_p1 = 'ᇂ' OR l_p1 = 'ᄒ' THEN l_p1 := 'ㅎ';
            ELSIF l_p1 = 'ᆩ' OR l_p1 = 'ᄁ' THEN l_p1 := 'ㄲ';
            ELSIF l_p1 = 'ᄄ' THEN l_p1 := 'ㄸ';
            ELSIF l_p1 = 'ᄈ' THEN l_p1 := 'ㅃ';
            ELSIF l_p1 = 'ᄊ' OR l_p1 = 'ᆻ' THEN l_p1 := 'ㅆ';
            ELSIF l_p1 = 'ᄍ' THEN l_p1 := 'ㅉ';
            ELSIF l_p1 = 'ᆪ' THEN l_p1 := 'ᄀᄉ';
            ELSIF l_p1 = 'ᆬ' THEN l_p1 := 'ᄂᄌ';
            ELSIF l_p1 = 'ᆭ' THEN l_p1 := 'ᄂᄒ';
            ELSIF l_p1 = 'ᆰ' THEN l_p1 := 'ᄅᄀ';
            ELSIF l_p1 = 'ᆱ' THEN l_p1 := 'ᄅᄆ';
            ELSIF l_p1 = 'ᆲ' THEN l_p1 := 'ᄅᄇ';
            ELSIF l_p1 = 'ᆳ' THEN l_p1 := 'ᄅᄉ';
            ELSIF l_p1 = 'ᆴ' THEN l_p1 := 'ᄅᄐ';
            ELSIF l_p1 = 'ᆶ' THEN l_p1 := 'ᄅᄒ';
            ELSIF l_p1 = 'ᆹ' THEN l_p1 := 'ᄇᄉ';
            ELSIF l_p1 = 'ᅡ' THEN l_p1 := 'ㅏ';
            ELSIF l_p1 = 'ᅣ' THEN l_p1 := 'ㅑ';
            ELSIF l_p1 = 'ᅥ' THEN l_p1 := 'ㅓ';
            ELSIF l_p1 = 'ᅧ' THEN l_p1 := 'ㅕ';
            ELSIF l_p1 = 'ᅩ' THEN l_p1 := 'ㅗ';
            ELSIF l_p1 = 'ㅛ' THEN l_p1 := 'ㅛ';
            ELSIF l_p1 = 'ᅮ' THEN l_p1 := 'ㅜ';
            ELSIF l_p1 = 'ᅲ' THEN l_p1 := 'ㅠ';
            ELSIF l_p1 = 'ᅳ' THEN l_p1 := 'ㅡ';
            ELSIF l_p1 = 'ᅵ' THEN l_p1 := 'ㅣ';
            ELSIF l_p1 = 'ᅢ' THEN l_p1 := 'ㅐ';
            ELSIF l_p1 = 'ᅤ' THEN l_p1 := 'ㅒ';
            ELSIF l_p1 = 'ᅦ' THEN l_p1 := 'ㅔ';
            ELSIF l_p1 = 'ᅨ' THEN l_p1 := 'ㅖ';
            ELSIF l_p1 = 'ᅬ' THEN l_p1 := 'ㅚ';
            ELSIF l_p1 = 'ᅱ' THEN l_p1 := 'ㅟ';
            ELSIF l_p1 = 'ᅴ' THEN l_p1 := 'ㅢ';
            END IF;

            l_rt := l_rt || l_p1;

        END LOOP;

        RETURN l_rt;
    END f2;


BEGIN

    FOR i IN 1..LENGTH( i_p1 )
    LOOP
        l_rt := l_rt || f2( f1( SUBSTR(i_p1, i, 1) ) );
    END LOOP;

    RETURN l_rt;
END FN_GET_DIV_KO_CHAR;


