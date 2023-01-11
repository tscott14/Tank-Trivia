#version 400 core


out vec4 out_color;

uniform sampler2D sampler;

varying vec2 tex_coords;

void main(void)
{	
	vec4 textureColor = texture2D(sampler, tex_coords);
	if(textureColor.a < 0.5){ discard; }
	out_color = textureColor ;

}