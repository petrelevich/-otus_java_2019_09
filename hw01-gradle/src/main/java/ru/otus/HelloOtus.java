package ru.otus;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.reflect.ClassPath;

import java.io.IOException;

public class HelloOtus {

	public static final String PACKAGE_NAME = HelloOtus.class.getPackage().getName();

	public static void main(String[] args) throws IOException {

		System.out.println("Let's check Guava string joiner, it can skip nulls:");

		String text = getJoinString();
		System.out.println(text);

		System.out.printf("\n" + "Let's take 'murmur 128 bit' hashcode of String \"%s\":" +"\n", text);

		HashCode hashCode = getGuavaHashCode(text);
		System.out.println(hashCode);


		System.out.printf("\n" + "Let's get list of all classes in the package \"%s\":" +"\n", PACKAGE_NAME);

		String classes = getToplevelClasses();
		System.out.println(classes);

	}

	private static String getToplevelClasses() throws IOException {
		ClassPath classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
		ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses(PACKAGE_NAME);
		return topLevelClasses.toString();
	}

	private static HashCode getGuavaHashCode(String join) {
		HashFunction hashFunction = Hashing.murmur3_128();
		HashCode hashCode = (HashCode) hashFunction.newHasher()
			.putString(join, Charsets.UTF_8)
			.hash();
		return hashCode;
	}

	private static String getJoinString() {
		String hello = "Hello";
		String otus = "Otus";
		String empty = null;
		String xclamationMark = "!";
		return Joiner.on("").skipNulls().join(new String[]{hello, otus, empty, xclamationMark});
	}
}