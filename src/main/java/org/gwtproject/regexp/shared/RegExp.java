package org.gwtproject.regexp.shared;

import com.google.gwt.core.shared.GwtIncompatible;

public interface RegExp {


    public static RegExpFactory regexpFactory = new GetRegExpFactory().get();


    public static class GetClientRegExpFactory
    {
        public RegExpFactory get()
        {
            return new NativeRegExpFactory();
        }
    }

    public static class GetRegExpFactory extends GetClientRegExpFactory
    {
        @GwtIncompatible
        public RegExpFactory get()
        {
            return new JavaRegExpFactory();
        }
    }

    /**
     * Creates a regular expression object from a pattern with no flags.
     *
     * @param pattern the Javascript regular expression pattern to compile
     * @return a new regular expression
     * @throws RuntimeException if the pattern is invalid
     */
    static RegExp compile(String pattern) {
        return regexpFactory.compile(pattern);
    }

    /**
     * Creates a regular expression object from a pattern with no flags.
     *
     * @param pattern the Javascript regular expression pattern to compile
     * @param flags   the flags string, containing at most one occurence of {@code
     *                'g'} ({@link #getGlobal()}), {@code 'i'} ({@link #getIgnoreCase()}
     *                ), or {@code 'm'} ({@link #getMultiline()}).
     * @return a new regular expression
     * @throws RuntimeException if the pattern or the flags are invalid
     */
    static RegExp compile(String pattern, String flags) {
        return regexpFactory.compile(pattern, flags);
    }

    /**
     * Returns a literal pattern <code>String</code> for the specified
     * <code>String</code>.
     * <p>
     * <p>This method produces a <code>String</code> that can be used to
     * create a <code>RegExp</code> that would match the string
     * <code>s</code> as if it were a literal pattern.</p> Metacharacters
     * or escape sequences in the input sequence will be given no special
     * meaning.
     *
     * @param input The string to be literalized
     * @return A literal string replacement
     */
    static String quote(String input) {
        return regexpFactory.quote(input);
    }

    /**
     * Applies the regular expression to the given string. This call affects the
     * value returned by {@link #getLastIndex()} if the global flag is set.
     *
     * @param input the string to apply the regular expression to
     * @return a match result if the string matches, else {@code null}
     */
    MatchResult exec(String input);

    /**
     * Returns whether the regular expression captures all occurences of the
     * pattern.
     */
    boolean getGlobal();

    /**
     * Returns whether the regular expression ignores case.
     */
    boolean getIgnoreCase();

    /**
     * Returns the zero-based position at which to start the next match. The
     * return value is not defined if the global flag is not set. After a call
     * to {@link #exec(String)} or {@link #test(String)}, this method returns
     * the next position following the most recent match.
     *
     * @see #getGlobal()
     */
    int getLastIndex();

    /**
     * Returns whether '$' and '^' match line returns ('\n' and '\r') in addition
     * to the beginning or end of the string.
     */
    boolean getMultiline();

    /**
     * Returns the pattern string of the regular expression.
     */
    String getSource();

    /**
     * Returns the input string with the part(s) matching the regular expression
     * replaced with the replacement string. If the global flag is set, replaces
     * all matches of the regular expression. Otherwise, replaces the first match
     * of the regular expression. As per Javascript semantics, backslashes in the
     * replacement string get no special treatment, but the replacement string can
     * use the following special patterns:
     * <ul>
     * <li>$1, $2, ... $99 - inserts the n'th group matched by the regular
     * expression.
     * <li>$&amp; - inserts the entire string matched by the regular expression.
     * <li>$$ - inserts a $.
     * </ul>
     *
     * @param input       the string in which the regular expression is to be searched.
     * @param replacement the replacement string.
     * @return the input string with the regular expression replaced with the
     * replacement string.
     * @throws RuntimeException if {@code replacement} is invalid
     */
    String replace(String input, String replacement);

    /**
     * Sets the zero-based position at which to start the next match.
     */
    void setLastIndex(int lastIndex);

    /**
     * Splits the input string around matches of the regular expression. If the
     * regular expression is completely empty, splits the input string into its
     * constituent characters. If the regular expression is not empty but matches
     * an empty string, the results are not well defined.
     *
     * @param input the string to be split.
     * @return the strings split off, any of which may be empty.
     */
    SplitResult split(String input);

    /**
     * Splits the input string around matches of the regular expression. If the
     * regular expression is completely empty, splits the input string into its
     * constituent characters. If the regular expression is not empty but matches
     * an empty string, the results are not well defined.
     *
     * @param input the string to be split.
     * @param limit the maximum number of strings to split off and return,
     *              ignoring the rest of the input string. If negative, there is no
     *              limit.
     * @return the strings split off, any of which may be empty.
     */
    SplitResult split(String input, int limit);

    /**
     * Determines if the regular expression matches the given string. This call
     * affects the value returned by {@link #getLastIndex()} if the global flag is
     * not set. Equivalent to: {@code exec(input) != null}
     *
     * @param input the string to apply the regular expression to
     * @return whether the regular expression matches the given string.
     */
    boolean test(String input);
}
