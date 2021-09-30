#version 330 core

out vec4 fColor;

in vec2 texCoord;

uniform vec3 color;
uniform sampler2D texture1;

void main()
{
    fColor = texture(texture1, texCoord);
}