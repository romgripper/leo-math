package bin.xing.leomath;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MultiplicationController {

    @GetMapping("/multiplication/{timestamp}")
    public String createQuestions(@PathVariable String timestamp, @RequestParam(defaultValue = "20") int count, Model model) {
        model.addAttribute("verify-path", "multiplication/verify");
        model.addAttribute("questions", generateQuestions(count));
        return "questions";
    }

    private List<String> generateQuestions(int count) {
        List<String> questions = new ArrayList<>(count);
        while (count-- > 0) {
            int left = MathUtil.random(1, 9);
            int right = MathUtil.random(1, 9);
            String question = String.format("%d x %d =", left, right);
            System.out.println("Creating question: " + question);
            questions.add(question);
        }
        return questions;
    }
}
