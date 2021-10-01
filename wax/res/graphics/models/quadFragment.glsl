#version 330 core

out vec4 _fragment_color;

in vec2 _texture_coords;
uniform vec3 _color;
uniform sampler2D _sampler;

void main()
{
    if(_color == vec3(0.0f, 0.0f, 0.0f) && _texture_coords != vec2(0.0f, 0.0f))
        _fragment_color = texture(_sampler, _texture_coords);

    else if(_color != vec3(0.0f, 0.0f, 0.0f) && _texture_coords != vec2(0.0f, 0.0f))
        _fragment_color = texture(_sampler, _texture_coords) * vec4(_color, 1.0f);

    else if(_texture_coords == vec2(0.0f, 0.0f))
        _fragment_color = vec4(_color, 1.0f);
}