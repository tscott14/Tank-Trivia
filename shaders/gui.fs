#version 400 core

in vec2 tex_coords;

out vec4 out_color;

uniform sampler2D sampler;
uniform vec2 mousePos;

void main(void)
{
	vec4 textureColor = texture2D(sampler, tex_coords);
	if(textureColor.a < 0.9){ discard; }
	if(mousePos.x < 0.0)textureColor.g=textureColor.g+0.001;
	out_color = textureColor;

}