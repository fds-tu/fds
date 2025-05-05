package bg.tusofia.fcst.ksi.practikum.fds.utilities;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class BaseMapper<R, Re> {
    private final ModelMapper modelMapper;
    private final Class<R> input;
    private final Class<Re> output;

    public <T1, T2> T2 map(T1 source, Class<T2> destination) {
        return this.modelMapper.map(source, destination);
    }

    public <T> R mapToResource(T source) {
        return this.modelMapper.map(source, input);
    }

    public <T1> R map(R resource, T1 source) {
        this.modelMapper.map(source, resource);
        return resource;
    }

    public Re map(R source) { return this.modelMapper.map(source, output); }
}
