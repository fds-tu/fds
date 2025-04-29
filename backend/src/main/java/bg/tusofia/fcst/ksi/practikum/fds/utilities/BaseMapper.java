package bg.tusofia.fcst.ksi.practikum.fds.utilities;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class BaseMapper<C, E, R, Re> {
    private final ModelMapper modelMapper;
    private final Class<R> input;
    private final Class<Re> output;

    public <T1, T2> T2 map(T1 source, Class<T2> destination) {
        return this.modelMapper.map(source, destination);
    }

    public Re map(R source) { return this.modelMapper.map(source, output); }

    public R mapFromCreateDto(C createResourceDto) {
        return this.modelMapper.map(createResourceDto, input);
    }

    public R mapFromEditDto(E editResourceDto) {
        return this.modelMapper.map(editResourceDto, input);
    }

    public R map(R source, E editResourceDto) {
        this.modelMapper.map(editResourceDto, source);
        return source;
    }
}
