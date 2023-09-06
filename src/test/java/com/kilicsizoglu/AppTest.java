package com.kilicsizoglu;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException
     */
    @Test
    public void JsonParserTest() throws IOException
    {
        String json = "{\"quiz\":{\"sport\":{\"q1\":{\"question\":\"Which one is correct team name in NBA?\",\"options\":[\"New York Bulls\",\"Los Angeles Kings\",\"Golden State Warriros\",\"Huston Rocket\"],\"answer\":\"Huston Rocket\"}},\"maths\":{\"q1\":{\"question\":\"5 + 7 = ?\",\"options\":[\"10\",\"11\",\"12\",\"13\"],\"answer\":\"12\"},\"q2\":{\"question\":\"12 - 8 = ?\",\"options\":[\"1\",\"2\",\"3\",\"4\"],\"answer\":\"4\"}}}}";
        jsonParser parser = new jsonParser(json);
        Map<String, Object> object = parser.parse();
        String data = (String) object.get("0");
        parser = new jsonParser(data);
        object = parser.parse();
        String quizData = (String) object.get("quiz");
        parser = new jsonParser(quizData);
        object = parser.parse();
        String sportData = (String) object.get("sport");
        parser = new jsonParser(sportData);
        object = parser.parse();
        String q1 = (String) object.get("q1");
        parser = new jsonParser(q1);
        object = parser.parse();
        String question = (String) object.get("question");
    }
}
