import java.util.function.Function;

public abstract class Result<T> {

    interface Visitor<T, R> {
        R visitSuccess(T value);

        R visitFailure(Throwable cause);
    }

    abstract  <R> R accept(Visitor<T, R> visitor);

    static class Success<T> extends Result<T> {
        private final T value;

        public Success(T value) {
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<T, R> visitor) {
            return visitor.visitSuccess(value);
        }
    }

    static class Failure<T> extends Result<T> {
        private final Throwable cause;

        public Failure(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public <R> R accept(Visitor<T, R> visitor) {
            return visitor.visitFailure(cause);
        }
    }

    static <T> Result<T> success(T value) {
        return new Success<T>(value);
    }

    static <T> Result<T> failure(Throwable cause) {
        return new Failure<T>(cause);
    }

    <T2> Result<T2> andThen(Function<T, Result<T2>> f) {
        return accept(new Visitor<T, Result<T2>>() {
            @Override
            public Result<T2> visitSuccess(T value) {
                return f.apply(value);
            }

            @Override
            public Result<T2> visitFailure(Throwable cause) {
                return Result.failure(cause);
            }
        });
    }
}
