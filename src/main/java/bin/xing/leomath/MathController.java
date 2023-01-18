package bin.xing.leomath;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class MathController {

    private static final String QUESTIONS_PAGE = "questions";

    @GetMapping("/math/{operation}")
    public String createQuestions(@PathVariable Operation operation,
                                  @RequestParam(defaultValue = "20") int count,
                                  @RequestParam(defaultValue = "2") int min,
                                  @RequestParam(defaultValue = "9") int max,
                                  Model model) {
        model.addAttribute("questions",
                generateQuestions(operation, count, min, max));

        LocalDateTime aucklandDateTime = ZonedDateTime.now(ZoneId.of("Pacific/Auckland")).toLocalDateTime();
        String dateTime = aucklandDateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy EEEE HH:mm:ss", Locale.ENGLISH));
        String chineseDateTime = aucklandDateTime.format(DateTimeFormatter.ofPattern("yyyy年M月dd日EEEE", Locale.SIMPLIFIED_CHINESE));
        model.addAttribute("dateTime", dateTime);
        model.addAttribute("chineseDateTime", chineseDateTime);

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
