package telran.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public record Monitor(Lock read, Lock write, AtomicInteger coount) {

}
