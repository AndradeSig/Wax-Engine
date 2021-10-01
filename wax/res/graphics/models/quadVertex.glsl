#version 330 core

layout (location = 0) in vec3 _position;
layout (location = 1) in vec2 _texture;

uniform mat4 _transform;
out vec2 _texture_coords;

void main()
{
    gl_Position = _transform * vec4(_position, 1.0f);
    _texture_coords = _texture;
}