public class ProcessorGenerator {
    private static int id = 0;

    public static Processor newProcessor() {
        return new Processor(++id);
    }
}
