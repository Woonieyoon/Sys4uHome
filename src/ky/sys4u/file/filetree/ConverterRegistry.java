package ky.sys4u.file.filetree;

import java.util.HashMap;
import java.util.Map;

public class ConverterRegistry {

	private final Map<TypeOfSourceAndResponse<?, ?>, Convertable<?, ?>> convertMap = new HashMap<>();
	
	public Map<TypeOfSourceAndResponse<?, ?>, Convertable<?, ?>> getConvertMap() {
		return convertMap;
	}

	public void addConverter(Class<?> s, Class<?> r, Convertable<?, ?> convert) {
		convertMap.put(new TypeOfSourceAndResponse<>(s, r), convert);
	}
	
	public <S, R> R requestConvert(S s, Class<R> response) {

		@SuppressWarnings("unchecked")
		Convertable<S, R> converted = (Convertable<S, R>) convertMap
				.get(new TypeOfSourceAndResponse<>(s.getClass(), response));
		return converted.convert(s);
	}

	private static class TypeOfSourceAndResponse<S, R> {
		Class<S> s;
		Class<R> r;

		public TypeOfSourceAndResponse(Class<S> s, Class<R> r) {
			this.s = s;
			this.r = r;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((r == null) ? 0 : r.hashCode());
			result = prime * result + ((s == null) ? 0 : s.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			TypeOfSourceAndResponse<S, R> other = (TypeOfSourceAndResponse<S, R>) obj;
			if (r == null) {
				if (other.r != null)
					return false;
			} else if (!r.equals(other.r))
				return false;
			if (s == null) {
				if (other.s != null)
					return false;
			} else if (!s.equals(other.s))
				return false;
			return true;
		}

	}

}
