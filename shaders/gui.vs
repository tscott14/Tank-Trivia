#version 400 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uvs;

varying vec2 tex_coords;

void main()
{
	tex_coords = uvs;
	gl_Position = vec4(position,1);
}