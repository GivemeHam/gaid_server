package com.example.demo.util;

import java.util.List;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import kr.co.shineware.util.common.model.Pair;

public class MorphUtil {
	public String komoran(String input_string){
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
	    komoran.setFWDic("user_data/fwd.user");
	    komoran.setUserDic("user_data/dic.user");

	    //String input = "밀리언 달러 베이비랑 바람과 함께 사라지다랑 뭐가 더 재밌었어?";
	    String input = input_string;
	    KomoranResult analyzeResultList = komoran.analyze(input);
	    List<kr.co.shineware.nlp.komoran.model.Token> tokenList = analyzeResultList.getTokenList();
/*
	    // 1. print each tokens by getTokenList()
	    System.out.println("==========print 'getTokenList()'==========");
	    for (Token token : tokenList) {
	      System.out.println(token);
	      System.out.println(token.getMorph()+"/"+token.getPos()+"("+token.getBeginIndex()+","+token.getEndIndex()+")");
	      System.out.println();
	    }

	    // 2. print nouns
	    System.out.println("==========print 'getNouns()'==========");
	    System.out.println(analyzeResultList.getNouns());
	    System.out.println();
	    
	    // 4. print analyzed result as list
	    System.out.println("==========print 'getList()'==========");
	    System.out.println(analyzeResultList.getList());
	    System.out.println();
	    
	     // 5. print morphes with selected pos
	    System.out.println("==========print 'getMorphesByTags()'==========");
	    System.out.println(analyzeResultList.getMorphesByTags("NP", "NNP", "JKB"));
*/
	    // 3. print analyzed result as pos-tagged text
	    System.out.println("==========print 'getPlainText()'==========");
	    System.out.println(analyzeResultList.getPlainText());
	    System.out.println();
		
		return analyzeResultList.getPlainText();
		
	}
}
