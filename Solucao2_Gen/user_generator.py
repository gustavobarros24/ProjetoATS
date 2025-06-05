from dataclasses import dataclass
from hypothesis import strategies as st
from hypothesis.errors import NonInteractiveExampleWarning

from warnings import filterwarnings 
filterwarnings("ignore", category=NonInteractiveExampleWarning)

name_strategy = st.text(
    min_size=2, 
    max_size=50,
    alphabet=st.characters(
        min_codepoint=32, 
        max_codepoint=126,
        exclude_characters=['"', "'", "\\", "/", ":", ";", "<", ">", "|"],
    )
).map(lambda x: f'\"{x}\"')

phone_strategy = st.text(
    min_size=9,
    max_size=15,
    alphabet=st.characters(
        min_codepoint=48,  # '0'
        max_codepoint=57,  # '9'
    )
).map(lambda x: f'\"{x}\"')

address_strategy = st.text(
    min_size=5,
    max_size=100,
    alphabet=st.characters(
        min_codepoint=32,
        max_codepoint=126,
        exclude_characters=['"', "'", "\\"]
    )
).map(lambda x: f'\"{x}\"')

email_strategy       = st.emails().map(lambda x: f'\"{x}\"')
age_strategy         = st.integers(min_value=0, max_value=120)
gender_strategy      = st.sampled_from(["Gender.Male","Gender.Female","Gender.Other"])
weight_strategy      = st.floats(min_value=1.0, max_value=300.0).map(lambda x: f"{x}f")
height_strategy      = st.integers(min_value=30, max_value=250)
bpm_strategy         = st.integers(min_value=30, max_value=220)
level_strategy       = st.integers(min_value=0, max_value=100)
frequency_strategy   = st.integers(min_value=1, max_value=100)

@st.composite
def arbitrary_user(draw):
    
    return {
        'name'           : draw(name_strategy),
        'address'        : draw(address_strategy), 
        'phone'          : draw(phone_strategy), 
        'email'          : draw(email_strategy),         
        'age'            : draw(age_strategy),              
        'gender'         : draw(gender_strategy),          
        'weight'         : draw(weight_strategy), 
        'height'         : draw(height_strategy), 
        'bpm'            : draw(bpm_strategy), 
        'level'          : draw(level_strategy),
        'frequency'      : draw(frequency_strategy),
    }

def user_to_string(user):
    parts = [
        f"Name: {user['name']}",
        f"Address: {user['address']}",
        f"Phone: {user['phone']}",
        f"Email: {user['email']}",
        f"Age: {user['age']}",
        f"Gender: {user['gender']}",
        f"Weight: {user['weight']}",
        f"Height: {user['height']}",
        f"BPM: {user['bpm']}",
        f"Level: {user['level']}",
        f"Frequency: {user['frequency']}",
    ]
    return f"{{ {", ".join(parts)} }}"

def formatted_users(user_list, show_frequency=False):
    user_args = (
        lambda user: (
            "{" 
            + f" {user['name']}, {user['age']}, {user['gender']}, {user['weight']}, {user['height']}, {user['bpm']}, {user['level']}, {user['address']}, {user['phone']}, {user['email']}" 
            + (f", {user['frequency']}" if show_frequency else "")
            + "}"
        )
    )
    
    return ",\n".join(user_args(u) for u in user_list)

def gen(amount=1): return [arbitrary_user().example() for _ in range(amount)]

if __name__ == "__main__":
    
    for x in gen(20): print(user_to_string(x))