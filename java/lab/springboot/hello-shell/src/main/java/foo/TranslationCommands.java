package foo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
public class TranslationCommands {
    @ShellMethod("Translate text from one language to another.")
    public String translate(@ShellOption String text, @ShellOption(defaultValue="en_US") Locale from, @ShellOption Locale to) {
        System.out.println("text: " + text);
        System.out.println(from + "->" + to);
        return "Test";
    }
}
