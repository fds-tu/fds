package bg.tusofia.fcst.ksi.practikum.fds.utilities.interfaces;

@FunctionalInterface
public interface CreateRelationCallback<T, RP, RS> {
     T call(RP primary, RS secondary);
}
