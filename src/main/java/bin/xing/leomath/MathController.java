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
                generateQuestions(operation, count, min, max));
        return QUESTIONS_PAGE;
    }

    private List<String> generateQuestions(Operation operation, int count, int min, int max) {
        List<String> questions = new ArrayList<>(count);
        while (questions.size() != count) {
            String question = generateQuestion(operation, min, max);
            if (!questions.contains(question)) {
                System.out.println("Creating question: " + question);
                questions.add(question);
            }
        }
        return questions;
    }

    private String generateQuestion(Operation operation, int min, int max) {
        int left = MathUtil.random(min, max);
        int right = MathUtil.random(min, max);

        if (operation == Operation.DIVISION) {
            left *= right;
        } else if (operation == Operation.SUBTRACTION && left < right) {
            int temp = left;
            left = right;
            right = temp;
        }
        return String.format("%d %c %d =", left, operation.getOperator(), right);
    }
}
