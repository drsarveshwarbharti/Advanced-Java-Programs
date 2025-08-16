/* Here’s a self-contained Swing Quiz App (single file) with:
-Multiple-choice and short-answer questions
-Next/Prev navigation + progress
-Countdown timer (auto-submits on time up)
-Score calculation and a simple review dialog */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuizApp {

    // ----- Model -----
    enum Type { MCQ, TEXT }

    static class Question {
        final String prompt;
        final Type type;
        final String[] options;     // null for TEXT
        final int correctIndex;     // for MCQ
        final String correctText;   // for TEXT (case-insensitive)
        Integer userChoice = null;  // for MCQ
        String userText = "";       // for TEXT

        // MCQ constructor
        Question(String prompt, String[] options, int correctIndex) {
            this.prompt = prompt;
            this.type = Type.MCQ;
            this.options = options;
            this.correctIndex = correctIndex;
            this.correctText = null;
        }

        // TEXT constructor
        Question(String prompt, String correctText) {
            this.prompt = prompt;
            this.type = Type.TEXT;
            this.options = null;
            this.correctIndex = -1;
            this.correctText = correctText;
        }

        boolean isCorrect() {
            if (type == Type.MCQ) {
                return userChoice != null && userChoice == correctIndex;
            } else {
                return userText != null && userText.trim().equalsIgnoreCase(correctText.trim());
            }
        }
    }

    static class QuizModel {
        final List<Question> questions = new ArrayList<>();
        int current = 0;
        int totalSeconds = 120; // quiz duration

        int score() {
            int s = 0;
            for (Question q : questions) if (q.isCorrect()) s++;
            return s;
        }
    }

    // ----- View -----
    static class QuizView extends JFrame {
        final JLabel title = new JLabel("Java Quiz", SwingConstants.LEFT);
        final JLabel timer = new JLabel("Time: 00:00", SwingConstants.RIGHT);
        final JProgressBar progress = new JProgressBar(0, 100);

        final JTextArea questionArea = new JTextArea(3, 40);
        final JPanel answerPanel = new JPanel(new CardLayout());
        // MCQ controls
        final JPanel mcqPanel = new JPanel();
        final ButtonGroup mcqGroup = new ButtonGroup();
        final JRadioButton[] mcqButtons = new JRadioButton[4];
        // TEXT controls
        final JPanel textPanel = new JPanel(new BorderLayout(6, 6));
        final JTextField textAnswer = new JTextField();

        final JButton prevBtn = new JButton("◀ Prev");
        final JButton nextBtn = new JButton("Next ▶");
        final JButton submitBtn = new JButton("Submit ✅");

        QuizView() {
            super("Swing Quiz App");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout(10, 10));
            getRootPane().setBorder(new EmptyBorder(10,10,10,10));

            // Top bar: title + timer
            JPanel top = new JPanel(new BorderLayout());
            title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
            top.add(title, BorderLayout.WEST);
            top.add(timer, BorderLayout.EAST);
            add(top, BorderLayout.NORTH);

            // Center: question + answers
            questionArea.setEditable(false);
            questionArea.setLineWrap(true);
            questionArea.setWrapStyleWord(true);
            questionArea.setFont(new Font("Serif", Font.PLAIN, 16));
            questionArea.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
            add(new JScrollPane(questionArea,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

            // MCQ panel
            mcqPanel.setLayout(new GridLayout(0, 1, 6, 6));
            for (int i = 0; i < mcqButtons.length; i++) {
                mcqButtons[i] = new JRadioButton("Option " + (i + 1));
                mcqGroup.add(mcqButtons[i]);
                mcqPanel.add(mcqButtons[i]);
            }

            // TEXT panel
            textPanel.add(new JLabel("Your answer:"), BorderLayout.WEST);
            textPanel.add(textAnswer, BorderLayout.CENTER);

            answerPanel.add(mcqPanel, "MCQ");
            answerPanel.add(textPanel, "TEXT");
            add(answerPanel, BorderLayout.SOUTH);

            // Bottom bar: progress + nav buttons
            JPanel bottom = new JPanel(new BorderLayout(8, 8));
            progress.setStringPainted(true);
            bottom.add(progress, BorderLayout.CENTER);

            JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
            nav.add(prevBtn);
            nav.add(nextBtn);
            nav.add(submitBtn);
            bottom.add(nav, BorderLayout.EAST);
            add(bottom, BorderLayout.PAGE_END);

            setSize(640, 420);
            setLocationRelativeTo(null);
        }
    }

    // ----- Controller -----
    static class QuizController {
        final QuizModel model;
        final QuizView view;
        final Timer swingTimer;

        QuizController(QuizModel model, QuizView view) {
            this.model = model;
            this.view = view;

            // Timer tick each second
            swingTimer = new Timer(1000, e -> {
                model.totalSeconds--;
                updateTimerLabel();
                if (model.totalSeconds <= 0) {
                    swingTimer.stop();
                    submitQuiz();
                }
            });

            // Listeners
            view.prevBtn.addActionListener(e -> move(-1));
            view.nextBtn.addActionListener(e -> move(+1));
            view.submitBtn.addActionListener(e -> submitQuiz());

            for (int i = 0; i < view.mcqButtons.length; i++) {
                final int idx = i;
                view.mcqButtons[i].addActionListener(e -> {
                    Question q = model.questions.get(model.current);
                    q.userChoice = idx;
                    updateProgress();
                });
            }
            view.textAnswer.addActionListener(e -> saveTextAnswer());
            view.textAnswer.addFocusListener(new FocusAdapter() {
                @Override public void focusLost(FocusEvent e) { saveTextAnswer(); }
            });

            // Init UI
            updateTimerLabel();
            showQuestion();
            swingTimer.start();
        }

        void updateTimerLabel() {
            int t = Math.max(0, model.totalSeconds);
            int m = t / 60, s = t % 60;
            view.timer.setText(String.format("Time: %02d:%02d", m, s));
        }

        void move(int delta) {
            saveTextAnswer();
            int n = model.questions.size();
            model.current = Math.max(0, Math.min(n - 1, model.current + delta));
            showQuestion();
        }

        void saveTextAnswer() {
            Question q = model.questions.get(model.current);
            if (q.type == Type.TEXT) {
                q.userText = view.textAnswer.getText();
                updateProgress();
            }
        }

        void showQuestion() {
            Question q = model.questions.get(model.current);
            view.title.setText("Java Quiz — Q" + (model.current + 1) + "/" + model.questions.size());
            view.questionArea.setText(q.prompt);

            CardLayout cl = (CardLayout) view.answerPanel.getLayout();
            if (q.type == Type.MCQ) {
                cl.show(view.answerPanel, "MCQ");
                view.mcqGroup.clearSelection();
                for (int i = 0; i < view.mcqButtons.length; i++) {
                    if (q.options != null && i < q.options.length) {
                        view.mcqButtons[i].setText(q.options[i]);
                        view.mcqButtons[i].setVisible(true);
                    } else {
                        view.mcqButtons[i].setVisible(false);
                    }
                }
                if (q.userChoice != null && q.userChoice >= 0 && q.userChoice < view.mcqButtons.length) {
                    view.mcqButtons[q.userChoice].setSelected(true);
                }
            } else {
                cl.show(view.answerPanel, "TEXT");
                view.textAnswer.setText(q.userText == null ? "" : q.userText);
                SwingUtilities.invokeLater(() -> view.textAnswer.requestFocusInWindow());
            }

            // nav buttons
            view.prevBtn.setEnabled(model.current > 0);
            view.nextBtn.setEnabled(model.current < model.questions.size() - 1);
            view.nextBtn.setText(model.current == model.questions.size() - 1 ? "Review ▶" : "Next ▶");

            updateProgress();
        }

        void updateProgress() {
            int answered = 0;
            for (Question q : model.questions) {
                if (q.type == Type.MCQ && q.userChoice != null) answered++;
                if (q.type == Type.TEXT && q.userText != null && !q.userText.trim().isEmpty()) answered++;
            }
            int pct = (int) Math.round(100.0 * answered / model.questions.size());
            view.progress.setValue(pct);
            view.progress.setString("Answered " + answered + " / " + model.questions.size());
        }

        void submitQuiz() {
            saveTextAnswer();
            int score = model.score();
            swingTimer.stop();

            // Build review text
            StringBuilder review = new StringBuilder();
            for (int i = 0; i < model.questions.size(); i++) {
                Question q = model.questions.get(i);
                review.append("Q").append(i + 1).append(": ").append(q.prompt).append("\n");
                if (q.type == Type.MCQ) {
                    String user = q.userChoice == null ? "(no answer)" :
                            q.options[q.userChoice];
                    String correct = q.options[q.correctIndex];
                    review.append("Your answer: ").append(user).append("\n");
                    review.append("Correct answer: ").append(correct).append("\n");
                } else {
                    String user = (q.userText == null || q.userText.isBlank()) ? "(no answer)" : q.userText;
                    review.append("Your answer: ").append(user).append("\n");
                    review.append("Expected: ").append(q.correctText).append("\n");
                }
                review.append(q.isCorrect() ? "✅ Correct\n\n" : "❌ Incorrect\n\n");
            }

            JTextArea area = new JTextArea(review.toString(), 20, 50);
            area.setEditable(false);
            JScrollPane sp = new JScrollPane(area);

            JOptionPane.showMessageDialog(
                    view,
                    new Object[]{
                            "Score: " + score + " / " + model.questions.size(),
                            sp
                    },
                    "Results",
                    JOptionPane.INFORMATION_MESSAGE
            );

            int opt = JOptionPane.showConfirmDialog(view, "Restart quiz?", "Restart",
                    JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                resetQuiz();
            } else {
                view.dispose();
            }
        }

        void resetQuiz() {
            for (Question q : model.questions) {
                q.userChoice = null;
                q.userText = "";
            }
            model.current = 0;
            model.totalSeconds = 120;
            swingTimer.start();
            showQuestion();
        }
    }

    // ----- Sample Data + Boot -----
    private static QuizModel sampleModel() {
        QuizModel m = new QuizModel();
        m.questions.add(new Question(
                "Which of these is NOT a Java primitive type?",
                new String[]{"int", "String", "double", "boolean"}, 1));

        m.questions.add(new Question(
                "Output of: System.out.println(3 + 4 * 2);",
                new String[]{"14", "11", "7", "3"}, 1));

        m.questions.add(new Question(
                "Keyword used to prevent inheritance of a class?",
                new String[]{"static", "final", "private", "sealed"}, 1));

        m.questions.add(new Question(
                "Name the method that starts a Java program (exact signature keyword after access modifier).",
                "main"));

        m.questions.add(new Question(
                "Which Swing component is used for a single-line text input?",
                new String[]{"JTextArea", "JTextField", "JLabel", "JList"}, 1));

        return m;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            QuizModel model = sampleModel();
            QuizView view = new QuizView();
            new QuizController(model, view);
            view.setVisible(true);
        });
    }
}
