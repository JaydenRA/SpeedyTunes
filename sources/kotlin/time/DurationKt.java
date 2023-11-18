package kotlin.time;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a \u00101\u001a\u00020\u00072\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0002\u00106\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00102\u001a\u000203H\u0002\u001a)\u00108\u001a\u00020\u0005*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a)\u0010=\u001a\u000203*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\b2\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\u00052\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\b2\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00052\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00012\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"!\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"!\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"!\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"!\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"!\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"!\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"!\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"!\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006J"}, d2 = {"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "skipWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "substringWhile", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLkotlin/time/DurationUnit;)J", "(ILkotlin/time/DurationUnit;)J", "(JLkotlin/time/DurationUnit;)J", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: Duration.kt */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(long j) {
    }

    public static final long toDuration(int $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long) $this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration((long) $this$toDuration, unit);
    }

    public static final long toDuration(long $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        if (new LongRange(-maxNsInUnit, maxNsInUnit).contains($this$toDuration)) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, (long) MAX_MILLIS));
    }

    public static final long toDuration(double $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
        if (!Double.isNaN(valueInNs)) {
            long nanos = MathKt.roundToLong(valueInNs);
            if (new LongRange(-4611686018426999999L, MAX_NANOS).contains(nanos)) {
                return durationOfNanos(nanos);
            }
            return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS)));
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
    }

    public static final long getNanoseconds(int $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(long $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(double $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getMicroseconds(int $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(long $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(double $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMilliseconds(int $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(long $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(double $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getSeconds(int $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getSeconds(long $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getSeconds(double $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getMinutes(int $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getMinutes(long $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getMinutes(double $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getHours(int $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getHours(long $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getHours(double $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getDays(int $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    public static final long getDays(long $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    public static final long getDays(double $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    /* renamed from: times-mvk6XK0  reason: not valid java name */
    private static final long m1500timesmvk6XK0(int $this$times_u2dmvk6XK0, long duration) {
        return Duration.m1412timesUwyO8pc(duration, $this$times_u2dmvk6XK0);
    }

    /* renamed from: times-kIfJnKk  reason: not valid java name */
    private static final long m1499timeskIfJnKk(double $this$times_u2dkIfJnKk, long duration) {
        return Duration.m1411timesUwyO8pc(duration, $this$times_u2dkIfJnKk);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x00df A[EDGE_INSN: B:170:0x00df->B:48:0x00df ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c4 A[LOOP:1: B:36:0x0085->B:46:0x00c4, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long parseDuration(java.lang.String r33, boolean r34) {
        /*
            r6 = r33
            int r7 = r33.length()
            if (r7 == 0) goto L_0x03c4
            r0 = 0
            kotlin.time.Duration$Companion r1 = kotlin.time.Duration.Companion
            long r8 = r1.m1476getZEROUwyO8pc()
            java.lang.String r10 = "Infinity"
            char r1 = r6.charAt(r0)
            r2 = 43
            r3 = 45
            r11 = 0
            if (r1 != r2) goto L_0x001e
        L_0x001c:
            r1 = 1
            goto L_0x0022
        L_0x001e:
            if (r1 != r3) goto L_0x0021
            goto L_0x001c
        L_0x0021:
            r1 = r11
        L_0x0022:
            if (r1 == 0) goto L_0x0026
            int r0 = r0 + 1
        L_0x0026:
            r13 = r0
            if (r13 <= 0) goto L_0x002b
            r0 = 1
            goto L_0x002c
        L_0x002b:
            r0 = r11
        L_0x002c:
            r14 = r0
            r0 = 0
            r1 = 2
            if (r14 == 0) goto L_0x003c
            r2 = r6
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = kotlin.text.StringsKt.startsWith$default((java.lang.CharSequence) r2, (char) r3, (boolean) r11, (int) r1, (java.lang.Object) r0)
            if (r2 == 0) goto L_0x003c
            r2 = 1
            goto L_0x003d
        L_0x003c:
            r2 = r11
        L_0x003d:
            r15 = r2
            java.lang.String r5 = "No components"
            if (r7 <= r13) goto L_0x03bd
            char r2 = r6.charAt(r13)
            r3 = 80
            java.lang.String r4 = "this as java.lang.String).substring(startIndex)"
            java.lang.String r12 = "Unexpected order of duration components"
            r16 = r5
            java.lang.String r1 = "this as java.lang.String…ing(startIndex, endIndex)"
            java.lang.String r11 = "null cannot be cast to non-null type java.lang.String"
            if (r2 != r3) goto L_0x01d8
            int r13 = r13 + 1
            if (r13 == r7) goto L_0x01d2
            java.lang.String r2 = "+-."
            r3 = 0
            r16 = 0
            r19 = r16
        L_0x0060:
            if (r13 >= r7) goto L_0x01c4
            char r0 = r6.charAt(r13)
            r5 = 84
            if (r0 != r5) goto L_0x0078
            if (r3 != 0) goto L_0x0072
            int r13 = r13 + 1
            if (r13 == r7) goto L_0x0072
            r3 = 1
            goto L_0x0060
        L_0x0072:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x0078:
            r0 = r33
            r5 = 0
            r16 = r0
            r21 = 0
            r22 = r13
            r23 = r5
            r5 = r22
        L_0x0085:
            r22 = r15
            int r15 = r16.length()
            if (r5 >= r15) goto L_0x00d3
            r24 = r14
            r15 = r16
            char r14 = r15.charAt(r5)
            r16 = 0
            r25 = r15
            kotlin.ranges.CharRange r15 = new kotlin.ranges.CharRange
            r20 = r7
            r26 = r10
            r7 = 48
            r10 = 57
            r15.<init>(r7, r10)
            boolean r15 = r15.contains((char) r14)
            if (r15 != 0) goto L_0x00bd
            r15 = r2
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            r18 = r2
            r2 = 0
            r7 = 0
            r10 = 2
            boolean r15 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r15, (char) r14, (boolean) r2, (int) r10, (java.lang.Object) r7)
            if (r15 == 0) goto L_0x00bb
            goto L_0x00c1
        L_0x00bb:
            r2 = 0
            goto L_0x00c2
        L_0x00bd:
            r18 = r2
            r7 = 0
            r10 = 2
        L_0x00c1:
            r2 = 1
        L_0x00c2:
            if (r2 == 0) goto L_0x00df
            int r5 = r5 + 1
            r2 = r18
            r7 = r20
            r15 = r22
            r14 = r24
            r16 = r25
            r10 = r26
            goto L_0x0085
        L_0x00d3:
            r18 = r2
            r20 = r7
            r26 = r10
            r24 = r14
            r25 = r16
            r7 = 0
            r10 = 2
        L_0x00df:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r11)
            java.lang.String r2 = r0.substring(r13, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            r0 = r2
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00f6
            r2 = 1
            goto L_0x00f7
        L_0x00f6:
            r2 = 0
        L_0x00f7:
            if (r2 != 0) goto L_0x01be
            int r2 = r0.length()
            int r13 = r13 + r2
            r2 = r6
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r13 < 0) goto L_0x01a1
            int r5 = kotlin.text.StringsKt.getLastIndex(r2)
            if (r13 > r5) goto L_0x01a1
            char r2 = r2.charAt(r13)
            int r13 = r13 + 1
            kotlin.time.DurationUnit r5 = kotlin.time.DurationUnitKt.durationUnitByIsoChar(r2, r3)
            r14 = r19
            if (r14 == 0) goto L_0x0127
            r15 = r5
            java.lang.Enum r15 = (java.lang.Enum) r15
            int r15 = r14.compareTo(r15)
            if (r15 <= 0) goto L_0x0121
            goto L_0x0127
        L_0x0121:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r12)
            throw r1
        L_0x0127:
            r19 = r5
            r27 = r0
            java.lang.CharSequence r27 = (java.lang.CharSequence) r27
            r28 = 46
            r29 = 0
            r30 = 0
            r31 = 6
            r32 = 0
            int r14 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r27, (char) r28, (int) r29, (boolean) r30, (int) r31, (java.lang.Object) r32)
            kotlin.time.DurationUnit r15 = kotlin.time.DurationUnit.SECONDS
            if (r5 != r15) goto L_0x0182
            if (r14 <= 0) goto L_0x0182
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r11)
            r15 = 0
            java.lang.String r7 = r0.substring(r15, r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)
            r15 = r11
            long r10 = parseOverLongIsoComponent(r7)
            long r10 = toDuration((long) r10, (kotlin.time.DurationUnit) r5)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r10)
            r10 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r10)
            java.lang.String r11 = r0.substring(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r4)
            r23 = r1
            r15 = r2
            double r1 = java.lang.Double.parseDouble(r11)
            long r1 = toDuration((double) r1, (kotlin.time.DurationUnit) r5)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r1)
            r11 = r10
            r2 = r18
            r7 = r20
            r15 = r22
            r1 = r23
            r14 = r24
            r10 = r26
            goto L_0x0060
        L_0x0182:
            r23 = r1
            r15 = r2
            r10 = r11
            long r1 = parseOverLongIsoComponent(r0)
            long r1 = toDuration((long) r1, (kotlin.time.DurationUnit) r5)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r1)
            r11 = r10
            r2 = r18
            r7 = r20
            r15 = r22
            r1 = r23
            r14 = r24
            r10 = r26
            goto L_0x0060
        L_0x01a1:
            r14 = r19
            r1 = r13
            r2 = 0
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Missing unit for value "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x01be:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>()
            throw r1
        L_0x01c4:
            r18 = r2
            r20 = r7
            r26 = r10
            r24 = r14
            r22 = r15
            r14 = r19
            goto L_0x03ae
        L_0x01d2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x01d8:
            r23 = r1
            r20 = r7
            r26 = r10
            r10 = r11
            r24 = r14
            r22 = r15
            if (r34 != 0) goto L_0x03b7
            r3 = 0
            int r7 = r20 - r13
            int r0 = r26.length()
            int r5 = java.lang.Math.max(r7, r0)
            r7 = 1
            r11 = 48
            r0 = r33
            r14 = r23
            r1 = r13
            r2 = r26
            r15 = r4
            r4 = r5
            r11 = r16
            r5 = r7
            boolean r0 = kotlin.text.StringsKt.regionMatches((java.lang.String) r0, (int) r1, (java.lang.String) r2, (int) r3, (int) r4, (boolean) r5)
            if (r0 == 0) goto L_0x020f
            kotlin.time.Duration$Companion r0 = kotlin.time.Duration.Companion
            long r8 = r0.m1474getINFINITEUwyO8pc()
            r7 = r20
            goto L_0x03ae
        L_0x020f:
            r0 = 0
            r1 = 0
            if (r24 != 0) goto L_0x0215
            r2 = 1
            goto L_0x0216
        L_0x0215:
            r2 = 0
        L_0x0216:
            if (r24 == 0) goto L_0x0239
            char r3 = r6.charAt(r13)
            r4 = 40
            if (r3 != r4) goto L_0x0239
            r3 = r6
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            char r3 = kotlin.text.StringsKt.last(r3)
            r4 = 41
            if (r3 != r4) goto L_0x0239
            r2 = 1
            int r13 = r13 + 1
            int r7 = r20 + -1
            if (r13 == r7) goto L_0x0233
            goto L_0x023b
        L_0x0233:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r11)
            throw r3
        L_0x0239:
            r7 = r20
        L_0x023b:
            if (r13 >= r7) goto L_0x03aa
            if (r1 == 0) goto L_0x0266
            if (r2 == 0) goto L_0x0266
            r3 = r33
            r4 = 0
            r5 = r13
        L_0x0245:
            int r11 = r3.length()
            if (r5 >= r11) goto L_0x0261
            char r11 = r3.charAt(r5)
            r16 = 0
            r18 = r1
            r1 = 32
            if (r11 != r1) goto L_0x0259
            r1 = 1
            goto L_0x025a
        L_0x0259:
            r1 = 0
        L_0x025a:
            if (r1 == 0) goto L_0x0263
            int r5 = r5 + 1
            r1 = r18
            goto L_0x0245
        L_0x0261:
            r18 = r1
        L_0x0263:
            r13 = r5
            goto L_0x0268
        L_0x0266:
            r18 = r1
        L_0x0268:
            r1 = 1
            r3 = r33
            r4 = 0
            r5 = r3
            r11 = 0
            r16 = r13
            r18 = r1
            r1 = r16
        L_0x0274:
            r16 = r2
            int r2 = r5.length()
            if (r1 >= r2) goto L_0x02aa
            char r2 = r5.charAt(r1)
            r19 = 0
            r20 = r4
            kotlin.ranges.CharRange r4 = new kotlin.ranges.CharRange
            r21 = r5
            r5 = 57
            r6 = 48
            r4.<init>(r6, r5)
            boolean r4 = r4.contains((char) r2)
            if (r4 != 0) goto L_0x029c
            r4 = 46
            if (r2 != r4) goto L_0x029a
            goto L_0x029c
        L_0x029a:
            r2 = 0
            goto L_0x029d
        L_0x029c:
            r2 = 1
        L_0x029d:
            if (r2 == 0) goto L_0x02b2
            int r1 = r1 + 1
            r6 = r33
            r2 = r16
            r4 = r20
            r5 = r21
            goto L_0x0274
        L_0x02aa:
            r20 = r4
            r21 = r5
            r5 = 57
            r6 = 48
        L_0x02b2:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r10)
            java.lang.String r1 = r3.substring(r13, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r14)
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x02c9
            r2 = 1
            goto L_0x02ca
        L_0x02c9:
            r2 = 0
        L_0x02ca:
            if (r2 != 0) goto L_0x03a4
            int r2 = r1.length()
            int r13 = r13 + r2
            r2 = r33
            r3 = 0
            r4 = r2
            r11 = 0
            r17 = r13
            r5 = r17
        L_0x02da:
            int r6 = r4.length()
            if (r5 >= r6) goto L_0x0306
            char r6 = r4.charAt(r5)
            r17 = 0
            r19 = r3
            kotlin.ranges.CharRange r3 = new kotlin.ranges.CharRange
            r21 = r4
            r4 = 97
            r23 = r11
            r11 = 122(0x7a, float:1.71E-43)
            r3.<init>(r4, r11)
            boolean r3 = r3.contains((char) r6)
            if (r3 == 0) goto L_0x030c
            int r5 = r5 + 1
            r3 = r19
            r4 = r21
            r11 = r23
            r6 = 48
            goto L_0x02da
        L_0x0306:
            r19 = r3
            r21 = r4
            r23 = r11
        L_0x030c:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r10)
            java.lang.String r3 = r2.substring(r13, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r14)
            r2 = r3
            int r3 = r2.length()
            int r13 = r13 + r3
            kotlin.time.DurationUnit r3 = kotlin.time.DurationUnitKt.durationUnitByShortName(r2)
            if (r0 == 0) goto L_0x0333
            r4 = r3
            java.lang.Enum r4 = (java.lang.Enum) r4
            int r4 = r0.compareTo(r4)
            if (r4 <= 0) goto L_0x032d
            goto L_0x0333
        L_0x032d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>(r12)
            throw r4
        L_0x0333:
            r0 = r3
            r27 = r1
            java.lang.CharSequence r27 = (java.lang.CharSequence) r27
            r28 = 46
            r29 = 0
            r30 = 0
            r31 = 6
            r32 = 0
            int r4 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r27, (char) r28, (int) r29, (boolean) r30, (int) r31, (java.lang.Object) r32)
            if (r4 <= 0) goto L_0x038b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r10)
            r5 = 0
            java.lang.String r6 = r1.substring(r5, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r14)
            r17 = r12
            long r11 = java.lang.Long.parseLong(r6)
            long r11 = toDuration((long) r11, (kotlin.time.DurationUnit) r3)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r11)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r10)
            java.lang.String r11 = r1.substring(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r15)
            double r11 = java.lang.Double.parseDouble(r11)
            long r11 = toDuration((double) r11, (kotlin.time.DurationUnit) r3)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r11)
            if (r13 < r7) goto L_0x0383
            r6 = r33
            r2 = r16
            r12 = r17
            r1 = r18
            goto L_0x023b
        L_0x0383:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Fractional component must be last"
            r5.<init>(r10)
            throw r5
        L_0x038b:
            r17 = r12
            r5 = 0
            long r11 = java.lang.Long.parseLong(r1)
            long r11 = toDuration((long) r11, (kotlin.time.DurationUnit) r3)
            long r8 = kotlin.time.Duration.m1410plusLRDsOJo(r8, r11)
            r6 = r33
            r2 = r16
            r12 = r17
            r1 = r18
            goto L_0x023b
        L_0x03a4:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r2.<init>()
            throw r2
        L_0x03aa:
            r18 = r1
            r16 = r2
        L_0x03ae:
            if (r22 == 0) goto L_0x03b5
            long r0 = kotlin.time.Duration.m1426unaryMinusUwyO8pc(r8)
            goto L_0x03b6
        L_0x03b5:
            r0 = r8
        L_0x03b6:
            return r0
        L_0x03b7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x03bd:
            r11 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r11)
            throw r0
        L_0x03c4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The string is empty"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseDuration(java.lang.String, boolean):long");
    }

    private static final long parseOverLongIsoComponent(String value) {
        Iterable $this$all$iv;
        int length = value.length();
        int startIndex = 0;
        if (length > 0 && StringsKt.contains$default((CharSequence) "+-", value.charAt(0), false, 2, (Object) null)) {
            startIndex = 0 + 1;
        }
        if (length - startIndex > 16) {
            Iterable $this$all$iv2 = new IntRange(startIndex, StringsKt.getLastIndex(value));
            if (!($this$all$iv2 instanceof Collection) || !((Collection) $this$all$iv2).isEmpty()) {
                Iterator it = $this$all$iv2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (new CharRange('0', '9').contains(value.charAt(((IntIterator) it).nextInt())) == 0) {
                            $this$all$iv = null;
                            break;
                        }
                    } else {
                        $this$all$iv = 1;
                        break;
                    }
                }
            } else {
                $this$all$iv = 1;
            }
            if ($this$all$iv != null) {
                return value.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
        }
        return StringsKt.startsWith$default(value, "+", false, 2, (Object) null) ? Long.parseLong(StringsKt.drop(value, 1)) : Long.parseLong(value);
    }

    private static final String substringWhile(String $this$substringWhile, int startIndex, Function1<? super Character, Boolean> predicate) {
        String $this$skipWhile$iv = $this$substringWhile;
        int i$iv = startIndex;
        while (i$iv < $this$skipWhile$iv.length() && predicate.invoke(Character.valueOf($this$skipWhile$iv.charAt(i$iv))).booleanValue()) {
            i$iv++;
        }
        Intrinsics.checkNotNull($this$substringWhile, "null cannot be cast to non-null type java.lang.String");
        String substring = $this$substringWhile.substring(startIndex, i$iv);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    private static final int skipWhile(String $this$skipWhile, int startIndex, Function1<? super Character, Boolean> predicate) {
        int i = startIndex;
        while (i < $this$skipWhile.length() && predicate.invoke(Character.valueOf($this$skipWhile.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static final long nanosToMillis(long nanos) {
        return nanos / ((long) NANOS_IN_MILLIS);
    }

    /* access modifiers changed from: private */
    public static final long millisToNanos(long millis) {
        return ((long) NANOS_IN_MILLIS) * millis;
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanos(long normalNanos) {
        return Duration.m1374constructorimpl(normalNanos << 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillis(long normalMillis) {
        return Duration.m1374constructorimpl((normalMillis << 1) + 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOf(long normalValue, int unitDiscriminator) {
        return Duration.m1374constructorimpl((normalValue << 1) + ((long) unitDiscriminator));
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long nanos) {
        if (new LongRange(-4611686018426999999L, MAX_NANOS).contains(nanos)) {
            return durationOfNanos(nanos);
        }
        return durationOfMillis(nanosToMillis(nanos));
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long millis) {
        if (new LongRange(-4611686018426L, MAX_NANOS_IN_MILLIS).contains(millis)) {
            return durationOfNanos(millisToNanos(millis));
        }
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, (long) MAX_MILLIS));
    }
}
