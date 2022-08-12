package bin.xing.leomath;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MathController {

    private static final String QUESTIONS_PAGE = "questions";

    @GetMapping("/{operation}/{timestamp}")
    public String createQuestions(@PathVariable String timestamp,
                                                @PathVariable Operation operation,
                                                @RequestParam(defaultValue = "20") int count,
                                                @RequestParam(defaultValue = "2") int min,
                                                @RequestParam(defaultValue = "9") int max,
                                                Model model) {
        model.addAttribute("questions",
                generateQuestions(operation.getOperator(), count, min, max));
        return QUESTIONS_PAGE;
    }

    private List<String> generateQuestions(char operator, int count, int min, int max) {
        List<String> questions = new ArrayList<>(count);
        while (questions.size() != count) {
            int left = MathUtil.random(min, max);
            int right = MathUtil.random(min, max);
            if (operator == '-' && left < right) {
                int temp = left;
                left = right;
                right = temp;
            }
            String question = String.format("%d %c %d =", left, operator, right);
            if (!questions.contains(question)) {
                System.out.println("Creating question: " + question);
                questions.add(question);
            }
        }
        return questions;
    }
}
