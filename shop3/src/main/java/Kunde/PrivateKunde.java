package Kunde;

import java.util.Set;

public class PrivatKunde extends AbstractKunde {
	private static final long serialVersionUID = -3177911520687689458L;
	
	private Set<HobbyType> hobbies;

	public Set<HobbyType> getHobbies() {
		return hobbies;
	}
	public void setHobbies(Set<HobbyType> hobbies) {
		this.hobbies = hobbies;
	}
	@Override
	public String toString() {
		return "Privatkunde [" + super.toString() + ", hobbies=" + hobbies + "]";
	}
}