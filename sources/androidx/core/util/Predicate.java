package androidx.core.util;

import java.util.Objects;

public interface Predicate<T> {
    boolean test(T t);

    Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return new Predicate$$ExternalSyntheticLambda1(this, other);
    }

    static /* synthetic */ boolean lambda$and$0(Predicate _this, Predicate other, Object t) {
        return _this.test(t) && other.test(t);
    }

    static /* synthetic */ boolean lambda$negate$1(Predicate _this, Object t) {
        return !_this.test(t);
    }

    Predicate<T> negate() {
        return new Predicate$$ExternalSyntheticLambda0(this);
    }

    Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return new Predicate$$ExternalSyntheticLambda2(this, other);
    }

    static /* synthetic */ boolean lambda$or$2(Predicate _this, Predicate other, Object t) {
        return _this.test(t) || other.test(t);
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
        if (targetRef == null) {
            return Predicate$$ExternalSyntheticLambda4.INSTANCE;
        }
        return new Predicate$$ExternalSyntheticLambda3(targetRef);
    }

    static <T> Predicate<T> not(Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return target.negate();
    }
}
