import io.sci_f.Core;

public class Main {

    public static void main(String[] args) {
        Core.init();
        Core compiler = Core.get();
        compiler.load();
        compiler.preprocess("");
        compiler.process();
        compiler.reset();
        compiler.end();
    }

}
